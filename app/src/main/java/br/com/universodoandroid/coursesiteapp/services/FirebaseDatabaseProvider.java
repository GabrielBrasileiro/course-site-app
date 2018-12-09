package br.com.universodoandroid.coursesiteapp.services;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.universodoandroid.coursesiteapp.modules.menu.MenuActivity;
import br.com.universodoandroid.coursesiteapp.modules.usersetup.UserSetupActivity;
import br.com.universodoandroid.coursesiteapp.utils.RequestCallback;

import static br.com.universodoandroid.coursesiteapp.constants.Constants.KEY_USERS_DATABASE_REFERENCE;

public class FirebaseDatabaseProvider {

    private Activity mActivity;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUsers;

    public FirebaseDatabaseProvider(Activity activity) {
        mActivity = activity;
        mAuth = FirebaseAuth.getInstance();

        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child(KEY_USERS_DATABASE_REFERENCE);
        mDatabaseUsers.keepSynced(true);
    }

    public void checkUserSettingsExists(RequestCallback.Database<Intent> callback) {
        if (mAuth.getCurrentUser() != null) {
            mDatabaseUsers.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Intent intent;
                    Class classReference;

                    if (dataSnapshot.hasChild(mAuth.getCurrentUser().getUid())) {
                        intent = new Intent(mActivity, MenuActivity.class);
                        classReference = MenuActivity.class;
                    } else {
                        intent = new Intent(mActivity, UserSetupActivity.class);
                        classReference = UserSetupActivity.class;
                    }

                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    callback.onSuccess(intent, classReference);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    callback.onError(databaseError.getMessage());
                }
            });
        } else {
            callback.onError("Don't have any user");
        }
    }

    private void initializeEventListener () {

    }

}

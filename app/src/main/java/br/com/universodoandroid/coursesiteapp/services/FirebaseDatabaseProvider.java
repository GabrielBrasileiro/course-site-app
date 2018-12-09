package br.com.universodoandroid.coursesiteapp.services;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.universodoandroid.coursesiteapp.domain.User;
import br.com.universodoandroid.coursesiteapp.modules.menu.MenuActivity;
import br.com.universodoandroid.coursesiteapp.modules.usersetup.UserSetupActivity;
import br.com.universodoandroid.coursesiteapp.utils.RequestCallback;

import static br.com.universodoandroid.coursesiteapp.constants.Constants.KEY_USERS_DATABASE_REFERENCE;
import static br.com.universodoandroid.coursesiteapp.constants.Constants.KEY_USER_IMAGE;
import static br.com.universodoandroid.coursesiteapp.constants.Constants.KEY_USER_NAME;

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
        initializeEventListener(mDatabaseUsers, new ServicesCallback() {
            @Override
            public void onSuccessResult(DataSnapshot dataSnapshot, FirebaseUser firebaseUser) {
                Intent intent;
                Class classReference;

                if (dataSnapshot.hasChild(firebaseUser.getUid())) {
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
            public void onFailureResult(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    public void getUserInformation(RequestCallback<User> callback) {
        DatabaseReference databaseUserInformation = mDatabaseUsers.child(mAuth.getCurrentUser().getUid());

        initializeEventListener(databaseUserInformation, new ServicesCallback() {
            @Override
            public void onSuccessResult(DataSnapshot dataSnapshot, FirebaseUser firebaseUser) {
                String name = (String) dataSnapshot.child(KEY_USER_NAME).getValue();
                String imageUrl = (String) dataSnapshot.child(KEY_USER_IMAGE).getValue();

                callback.onSuccess(new User(name, firebaseUser.getEmail(), Uri.parse(imageUrl)));
            }

            @Override
            public void onFailureResult(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    private void initializeEventListener(DatabaseReference databaseReference, ServicesCallback callback) {
        if (mAuth.getCurrentUser() != null) {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    callback.onSuccessResult(dataSnapshot, mAuth.getCurrentUser());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    callback.onFailureResult(databaseError.getMessage());
                }
            });
        } else {
            callback.onFailureResult("Don't have any user");
        }
    }

}

package br.com.universodoandroid.coursesiteapp.services;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import br.com.universodoandroid.coursesiteapp.domain.User;
import br.com.universodoandroid.coursesiteapp.modules.menu.MenuActivity;
import br.com.universodoandroid.coursesiteapp.modules.usersetup.UserSetupActivity;
import br.com.universodoandroid.coursesiteapp.services.base.FirebaseDatabaseProvider;
import br.com.universodoandroid.coursesiteapp.utils.RequestCallback;

import static br.com.universodoandroid.coursesiteapp.constants.Constants.KEY_USERS_DATABASE_REFERENCE;
import static br.com.universodoandroid.coursesiteapp.constants.Constants.KEY_USER_IMAGE;
import static br.com.universodoandroid.coursesiteapp.constants.Constants.KEY_USER_NAME;

public class FirebaseDatabaseUserProvider extends FirebaseDatabaseProvider {

    public FirebaseDatabaseUserProvider(Activity activity) {
        super(activity);
        setDatabaseReference(KEY_USERS_DATABASE_REFERENCE);
    }

    public void checkUserSettingsExists(RequestCallback.Database<Intent> callback) {
        initializeEventListener(getDatabaseReference(), new ServicesCallback() {
            @Override
            public void onSuccessResult(DataSnapshot dataSnapshot, FirebaseUser firebaseUser) {
                Intent intent;
                Class classReference;

                if (dataSnapshot.hasChild(firebaseUser.getUid())) {
                    intent = new Intent(getActivity(), MenuActivity.class);
                    classReference = MenuActivity.class;
                } else {
                    intent = new Intent(getActivity(), UserSetupActivity.class);
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
        DatabaseReference databaseUserInformation = getDatabaseReference().child(getAuthInstance().getCurrentUser().getUid());

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

}

package br.com.universodoandroid.coursesiteapp.services;

import android.app.Activity;
import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import br.com.universodoandroid.coursesiteapp.utils.RequestCallback;

import static br.com.universodoandroid.coursesiteapp.constants.Constants.KEY_PROFILE_IMAGE;
import static br.com.universodoandroid.coursesiteapp.constants.Constants.KEY_USERS_DATABASE_REFERENCE;
import static br.com.universodoandroid.coursesiteapp.constants.Constants.KEY_USER_IMAGE;
import static br.com.universodoandroid.coursesiteapp.constants.Constants.KEY_USER_NAME;

public class FirebaseStorageProvider {

    private DatabaseReference mDatabaseUsers;
    private StorageReference mStorageImage;
    private FirebaseAuth mAuth;
    private Activity mActivity;

    public FirebaseStorageProvider(Activity activity) {
        mActivity = activity;
        mAuth = FirebaseAuth.getInstance();

        mStorageImage = FirebaseStorage.getInstance().getReference().child(KEY_PROFILE_IMAGE);
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child(KEY_USERS_DATABASE_REFERENCE);
    }

    public void insertUserInformation(String userId, Uri imageUri, String name, RequestCallback<Void> callback) {
        StorageReference filepath = mStorageImage.child(imageUri.getLastPathSegment());

        filepath.putFile(imageUri).addOnSuccessListener(taskSnapshot -> filepath.getDownloadUrl().addOnSuccessListener(uri -> {
                mDatabaseUsers.child(userId).child(KEY_USER_NAME).setValue(name);
                mDatabaseUsers.child(userId).child(KEY_USER_IMAGE).setValue(uri.toString());

                callback.onSuccess(null);
        }));
    }

}

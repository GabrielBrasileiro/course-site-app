package br.com.universodoandroid.coursesiteapp.services;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import br.com.universodoandroid.coursesiteapp.utils.RequestCallback;

public class FirebaseAuthProvider {

    private FirebaseAuth mAuth;
    private Activity mActivity;

    public FirebaseAuthProvider(Activity activity) {
        mActivity = activity;
        mAuth = FirebaseAuth.getInstance();
    }

    public void signInWithEmailAndPassword(@NonNull String email, @NonNull String password, RequestCallback<FirebaseUser> callback) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(mActivity, task -> {
            if(task.isSuccessful()){
                callback.onSuccess(mAuth.getCurrentUser());
            } else {
                callback.onError(Objects.requireNonNull(task.getException()).getMessage());
            }
        });
    }

    public void signUpWithEmailAndPassword(@NonNull String email, @NonNull String password, RequestCallback<FirebaseUser> callback){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(mActivity, task -> {
            if(task.isSuccessful()){
                callback.onSuccess(mAuth.getCurrentUser());
            } else {
                callback.onError(Objects.requireNonNull(task.getException()).getMessage());
            }
        });
    }

    public void loginWithGoogle(){

    }

}

package br.com.universodoandroid.coursesiteapp.modules.login;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;

import br.com.universodoandroid.coursesiteapp.services.FirebaseAuthProvider;
import br.com.universodoandroid.coursesiteapp.utils.RequestCallback;

public class LoginPresenter implements LoginContract.Presenter{

    private FirebaseAuthProvider mFirebaseAuthProvider;
    private LoginContract.View mView;

    public LoginPresenter(@NonNull LoginContract.View view,
                          @NonNull FirebaseAuthProvider firebaseAuthProvider) {
        mView = view;
        mFirebaseAuthProvider = firebaseAuthProvider;

        mView.setPresenter(this);
    }

    @Override
    public void loginWithEmailAndPassword(String email, String password) {
        mFirebaseAuthProvider.signInWithEmailAndPassword(email, password, new RequestCallback<FirebaseUser>() {
            @Override
            public void onSuccess(FirebaseUser firebaseUser) {
                mView.onLoginSuccess(firebaseUser);
            }

            @Override
            public void onError(String errorMessage) {
                mView.onLoginFailed(errorMessage);
            }
        });
    }

    @Override
    public void loginWithGoogle() {

    }

}

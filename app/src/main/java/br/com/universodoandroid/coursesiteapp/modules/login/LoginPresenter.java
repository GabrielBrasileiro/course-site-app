package br.com.universodoandroid.coursesiteapp.modules.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseUser;

import br.com.universodoandroid.coursesiteapp.services.FirebaseAuthProvider;
import br.com.universodoandroid.coursesiteapp.services.FirebaseDatabaseProvider;
import br.com.universodoandroid.coursesiteapp.utils.RequestCallback;

public class LoginPresenter implements LoginContract.Presenter {

    private FirebaseDatabaseProvider mFirebaseDatabaseProvider;
    private FirebaseAuthProvider mFirebaseAuthProvider;
    private LoginContract.View mView;

    public LoginPresenter(@NonNull LoginContract.View view,
                          @NonNull FirebaseAuthProvider firebaseAuthProvider, FirebaseDatabaseProvider firebaseDatabaseProvider) {
        mView = view;
        mFirebaseAuthProvider = firebaseAuthProvider;
        mFirebaseDatabaseProvider = firebaseDatabaseProvider;

        mView.setPresenter(this);
    }

    @Override
    public void loginWithEmailAndPassword(String email, String password) {
        mView.showProgressBar();

        mFirebaseAuthProvider.signInWithEmailAndPassword(email, password, new RequestCallback<FirebaseUser>() {
            @Override
            public void onSuccess(FirebaseUser firebaseUser) {
                mView.onLoginSuccess(firebaseUser);
                mView.dismissProgressBar();
            }

            @Override
            public void onError(String errorMessage) {
                mView.onLoginFailed(errorMessage);
                mView.dismissProgressBar();
            }
        });
    }

    @Override
    public void loginWithGoogle() {

    }

    @Override
    public void checkUserSettingsExists() {
        mFirebaseDatabaseProvider.checkUserSettingsExists(new RequestCallback.Database<Intent>() {
            @Override
            public void onSuccess(Intent intent, Class classReference) {
                mView.setCorrectIntentByUserState(intent);
            }

            @Override
            public void onError(String errorMessage) {
                Log.e(this.getClass().toString(), errorMessage);
            }
        });
    }

}

package br.com.universodoandroid.coursesiteapp.modules.login;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;

import br.com.universodoandroid.coursesiteapp.database.Session;
import br.com.universodoandroid.coursesiteapp.modules.menu.MenuActivity;
import br.com.universodoandroid.coursesiteapp.services.FirebaseAuthProvider;
import br.com.universodoandroid.coursesiteapp.utils.RequestCallback;

public class LoginPresenter implements LoginContract.Presenter {

    private Session mSession;
    private FirebaseAuthProvider mFirebaseAuthProvider;
    private LoginContract.View mView;

    public LoginPresenter(@NonNull LoginContract.View view,
                          @NonNull FirebaseAuthProvider firebaseAuthProvider,
                          @NonNull Session session) {
        mView = view;
        mFirebaseAuthProvider = firebaseAuthProvider;
        mSession = session;

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
                mSession.saveStateLoginSuccess();
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
        if (mSession.isLoggedIn()) {
            mView.setCorrectIntentByUserState(new Intent(mSession.getActivity(), MenuActivity.class));
            mSession.getActivity().finish();
        }
    }

}

package br.com.universodoandroid.coursesiteapp.modules.login;

import com.google.firebase.auth.FirebaseUser;

import br.com.universodoandroid.coursesiteapp.modules.BaseView;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void onLoginSuccess(FirebaseUser firebaseUser);
        void onLoginFailed(String errorMessage);
        void showProgressBar();
        void dismissProgressBar();
    }

    interface Presenter {
        void loginWithEmailAndPassword(String email, String password);
        void loginWithGoogle();
    }
}

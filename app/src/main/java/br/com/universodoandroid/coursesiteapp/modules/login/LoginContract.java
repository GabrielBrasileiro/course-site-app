package br.com.universodoandroid.coursesiteapp.modules.login;

import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;

import br.com.universodoandroid.coursesiteapp.modules.BaseView;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void onLoginSuccess(FirebaseUser firebaseUser);
        void onLoginFailed(String errorMessage);
        void setCorrectIntentByUserState(Intent intent);
    }

    interface Presenter {
        void loginWithEmailAndPassword(String email, String password);
        void loginWithGoogle();
        void checkUserSettingsExists();
    }
}

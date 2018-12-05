package br.com.universodoandroid.coursesiteapp.modules.register;

import br.com.universodoandroid.coursesiteapp.modules.BaseView;

public interface RegisterContract {
    interface View extends BaseView<Presenter> {
        void onRegisterSuccess();
        void onRegisterFailure(String message);
    }

    interface Presenter {
        void signUpWithEmailAndPassword(String email, String password);
    }
}

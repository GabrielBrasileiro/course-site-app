package br.com.universodoandroid.coursesiteapp.modules.resetpassword;

import br.com.universodoandroid.coursesiteapp.modules.BaseView;

public interface ResetPasswordContract {
    interface View extends BaseView<Presenter> {
        void onRegisterSuccess();
        void onRegisterFailure(String message);
    }

    interface Presenter {
        void resetPassword(String email);
    }
}

package br.com.universodoandroid.coursesiteapp.modules.menu;

import android.content.Intent;

import br.com.universodoandroid.coursesiteapp.modules.BaseView;

public interface MenuContract {
    interface View extends BaseView<Presenter> {
        void setCorrectIntentByUserState(Intent intent, Class classReference);
    }

    interface Presenter {
        void checkUserSettings();
    }
}

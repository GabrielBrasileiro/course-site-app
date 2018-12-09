package br.com.universodoandroid.coursesiteapp.modules.usersetup;

import android.net.Uri;

import br.com.universodoandroid.coursesiteapp.modules.BaseView;

public interface UserSetupContract {
    interface View extends BaseView<Presenter> {
        void onInsertSuccess();
        void onInsertFailure(String errorMessage);
    }

    interface Presenter {
        void insertUserSettings(String userId, Uri imageUri, String name);
    }
}

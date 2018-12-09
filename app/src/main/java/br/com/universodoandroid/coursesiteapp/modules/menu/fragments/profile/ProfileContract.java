package br.com.universodoandroid.coursesiteapp.modules.menu.fragments.profile;

import br.com.universodoandroid.coursesiteapp.domain.User;
import br.com.universodoandroid.coursesiteapp.modules.BaseView;

public interface ProfileContract {
    interface View extends BaseView<Presenter> {
        void setUserInformation(User user);
    }

    interface Presenter {
        void getUserInformation();
    }
}

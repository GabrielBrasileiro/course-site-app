package br.com.universodoandroid.coursesiteapp.modules.menu.fragments.profile;

import android.util.Log;

import br.com.universodoandroid.coursesiteapp.domain.User;
import br.com.universodoandroid.coursesiteapp.services.FirebaseDatabaseProvider;
import br.com.universodoandroid.coursesiteapp.utils.RequestCallback;

class ProfilePresenter implements ProfileContract.Presenter {

    private FirebaseDatabaseProvider mFirebaseDatabaseProvider;
    private ProfileContract.View mView;

    public ProfilePresenter(ProfileContract.View view, FirebaseDatabaseProvider firebaseDatabaseProvider) {
        mView = view;
        mFirebaseDatabaseProvider = firebaseDatabaseProvider;

        mView.setPresenter(this);
    }

    @Override
    public void getUserInformation() {
        mView.showProgressBar();

        mFirebaseDatabaseProvider.getUserInformation(new RequestCallback<User>() {
            @Override
            public void onSuccess(User user) {
                mView.setUserInformation(user);
                mView.dismissProgressBar();
            }

            @Override
            public void onError(String errorMessage) {
                Log.e(this.getClass().toString(), errorMessage);
            }
        });
    }
}

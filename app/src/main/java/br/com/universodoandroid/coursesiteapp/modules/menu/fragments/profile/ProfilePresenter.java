package br.com.universodoandroid.coursesiteapp.modules.menu.fragments.profile;

import android.support.annotation.NonNull;
import android.util.Log;

import br.com.universodoandroid.coursesiteapp.domain.User;
import br.com.universodoandroid.coursesiteapp.services.FirebaseDatabaseUserProvider;
import br.com.universodoandroid.coursesiteapp.utils.RequestCallback;

class ProfilePresenter implements ProfileContract.Presenter {

    private FirebaseDatabaseUserProvider mFirebaseDatabaseUserProvider;
    private ProfileContract.View mView;

    public ProfilePresenter(@NonNull ProfileContract.View view,
                            @NonNull FirebaseDatabaseUserProvider firebaseDatabaseUserProvider) {
        mView = view;
        mFirebaseDatabaseUserProvider = firebaseDatabaseUserProvider;

        mView.setPresenter(this);
    }

    @Override
    public void getUserInformation() {
        mView.showProgressBar();

        mFirebaseDatabaseUserProvider.getUserInformation(new RequestCallback<User>() {
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

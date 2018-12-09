package br.com.universodoandroid.coursesiteapp.modules.usersetup;

import android.net.Uri;
import android.support.annotation.NonNull;

import br.com.universodoandroid.coursesiteapp.services.FirebaseStorageProvider;
import br.com.universodoandroid.coursesiteapp.utils.RequestCallback;

public class UserSetupPresenter implements UserSetupContract.Presenter {

    private UserSetupContract.View mView;
    private FirebaseStorageProvider mFirebaseStorageProvider;

    public UserSetupPresenter(@NonNull UserSetupContract.View view,
                              @NonNull FirebaseStorageProvider firebaseStorageProvider) {
        mView = view;
        mFirebaseStorageProvider = firebaseStorageProvider;

        mView.setPresenter(this);
    }

    @Override
    public void insertUserSettings(String userId, Uri imageUri, String name) {
        mView.showProgressBar();
        mFirebaseStorageProvider.insertUserInformations(userId, imageUri, name, new RequestCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                mView.onInsertSuccess();
                mView.dismissProgressBar();
            }

            @Override
            public void onError(String errorMessage) {
                mView.onInsertFailure(errorMessage);
                mView.dismissProgressBar();
            }
        });
    }

}

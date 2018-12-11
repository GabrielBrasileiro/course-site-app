package br.com.universodoandroid.coursesiteapp.modules.menu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import br.com.universodoandroid.coursesiteapp.services.FirebaseDatabaseUserProvider;
import br.com.universodoandroid.coursesiteapp.utils.RequestCallback;

public class MenuPresenter implements MenuContract.Presenter {

    private MenuContract.View mView;
    private FirebaseDatabaseUserProvider mFirebaseDatabaseUserProvider;

    public MenuPresenter(@NonNull MenuContract.View view,
                         @NonNull FirebaseDatabaseUserProvider firebaseDatabaseUserProvider) {
        mView = view;
        mFirebaseDatabaseUserProvider = firebaseDatabaseUserProvider;

        mView.setPresenter(this);
    }

    @Override
    public void checkUserSettings() {
        mFirebaseDatabaseUserProvider.checkUserSettingsExists(new RequestCallback.Database<Intent>() {
            @Override
            public void onSuccess(Intent result, Class classReference) {
                mView.setCorrectIntentByUserState(result, classReference);
            }

            @Override
            public void onError(String errorMessage) {
                Log.e(this.getClass().toString(), errorMessage);
            }
        });
    }
}

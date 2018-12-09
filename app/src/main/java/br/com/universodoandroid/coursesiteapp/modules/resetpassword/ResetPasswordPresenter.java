package br.com.universodoandroid.coursesiteapp.modules.resetpassword;

import android.support.annotation.NonNull;

import br.com.universodoandroid.coursesiteapp.services.FirebaseAuthProvider;
import br.com.universodoandroid.coursesiteapp.utils.RequestCallback;

public class ResetPasswordPresenter implements ResetPasswordContract.Presenter {

    private FirebaseAuthProvider mFirebaseAuthProvider;
    private ResetPasswordContract.View mView;

    public ResetPasswordPresenter(@NonNull ResetPasswordContract.View view,
                                  @NonNull FirebaseAuthProvider firebaseAuthProvider) {
        mView = view;
        mFirebaseAuthProvider = firebaseAuthProvider;

        mView.setPresenter(this);
    }

    @Override
    public void resetPassword(String email) {
        mView.showProgressBar();
        mFirebaseAuthProvider.resetPassword(email, new RequestCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                mView.onRegisterSuccess();
                mView.dismissProgressBar();
            }

            @Override
            public void onError(String errorMessage) {
                mView.onRegisterFailure(errorMessage);
                mView.dismissProgressBar();
            }
        });
    }

}

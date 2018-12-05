package br.com.universodoandroid.coursesiteapp.modules.register;

import android.support.annotation.NonNull;

import br.com.universodoandroid.coursesiteapp.services.FirebaseAuthProvider;
import br.com.universodoandroid.coursesiteapp.utils.RequestCallback;

public class RegisterPresenter implements RegisterContract.Presenter {

    private FirebaseAuthProvider mFirebaseAuthProvider;
    private RegisterContract.View mView;

    public RegisterPresenter(@NonNull RegisterContract.View view,
                             @NonNull FirebaseAuthProvider firebaseAuthProvider) {
        mView = view;
        mFirebaseAuthProvider = firebaseAuthProvider;

        mView.setPresenter(this);
    }

    @Override
    public void signUpWithEmailAndPassword(String email, String password) {
        mFirebaseAuthProvider.signUpWithEmailAndPassword(email, password, new RequestCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                mView.onRegisterSuccess();
            }

            @Override
            public void onError(String errorMessage) {
                mView.onRegisterFailure(errorMessage);
            }
        });
    }
}

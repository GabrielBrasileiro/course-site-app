package br.com.universodoandroid.coursesiteapp.modules.resetpassword;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import br.com.universodoandroid.coursesiteapp.R;
import br.com.universodoandroid.coursesiteapp.databinding.ActivityResetPasswordBinding;
import br.com.universodoandroid.coursesiteapp.services.FirebaseAuthProvider;

public class ResetPasswordActivity extends AppCompatActivity implements ResetPasswordContract.View {

    private ActivityResetPasswordBinding mActivityResetPasswordBinding;

    private ResetPasswordContract.Presenter mPresenter;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new ResetPasswordPresenter(this, new FirebaseAuthProvider(this));

        mActivityResetPasswordBinding = DataBindingUtil.setContentView(this, R.layout.activity_reset_password);
        mActivityResetPasswordBinding.setHandler(this);

        mActivityResetPasswordBinding.resetEmailButton.setOnClickListener(v -> {
            String email = mActivityResetPasswordBinding.emailTextView.getText().toString();

            if (!email.isEmpty()) {
                mPresenter.resetPassword(email);
            } else {
                Toast.makeText(this, "Verifique o campo de e-mail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRegisterSuccess() {
        Toast.makeText(this, "Confira o seu e-mail e prossiga com as infoirmações", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onRegisterFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(ResetPasswordContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgressBar() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Aguarde...");
        mProgressDialog.show();
    }

    @Override
    public void dismissProgressBar() {
        mProgressDialog.dismiss();
    }

}

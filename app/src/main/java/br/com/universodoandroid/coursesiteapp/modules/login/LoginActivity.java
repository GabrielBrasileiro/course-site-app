package br.com.universodoandroid.coursesiteapp.modules.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import br.com.universodoandroid.coursesiteapp.R;
import br.com.universodoandroid.coursesiteapp.database.Session;
import br.com.universodoandroid.coursesiteapp.databinding.ActivityLoginBinding;
import br.com.universodoandroid.coursesiteapp.modules.login.LoginContract.Presenter;
import br.com.universodoandroid.coursesiteapp.modules.menu.MenuActivity;
import br.com.universodoandroid.coursesiteapp.modules.register.RegisterActivity;
import br.com.universodoandroid.coursesiteapp.modules.resetpassword.ResetPasswordActivity;
import br.com.universodoandroid.coursesiteapp.services.FirebaseAuthProvider;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private ActivityLoginBinding mActivityLoginBinding;
    private Presenter mPresenter;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new LoginPresenter(this,
                new FirebaseAuthProvider(this),
                new Session(this));

        mActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mActivityLoginBinding.setHandler(this);

        mPresenter.checkUserSettingsExists();

        mActivityLoginBinding.forgotPasswordButton.setOnClickListener(view ->
                startActivity(new Intent(this, ResetPasswordActivity.class)));

        mActivityLoginBinding.registerButton.setOnClickListener(view ->
                startActivity(new Intent(this, RegisterActivity.class)));

        loginWithEmailAndPasswordSetupButton();
    }

    private void loginWithEmailAndPasswordSetupButton() {
        mActivityLoginBinding.loginButton.setOnClickListener(view -> {
            String email = mActivityLoginBinding.loginEditText.getText().toString().trim();
            String password = mActivityLoginBinding.passwordEditText.getText().toString().trim();

            if (!email.isEmpty() && !password.isEmpty()) {
                mPresenter.loginWithEmailAndPassword(email, password);
            } else {
                Toast.makeText(this, "Verifique os campos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setPresenter(Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onLoginSuccess(FirebaseUser firebaseUser) {
        Toast.makeText(this, firebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MenuActivity.class));
    }

    @Override
    public void onLoginFailed(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCorrectIntentByUserState(Intent intent) {
        startActivity(intent);
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

package br.com.universodoandroid.coursesiteapp.modules.register;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import br.com.universodoandroid.coursesiteapp.R;
import br.com.universodoandroid.coursesiteapp.databinding.ActivityRegisterBinding;
import br.com.universodoandroid.coursesiteapp.modules.register.RegisterContract.Presenter;
import br.com.universodoandroid.coursesiteapp.services.FirebaseAuthProvider;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {

    private ActivityRegisterBinding mActivityRegisterBinding;
    private Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new RegisterPresenter(this, new FirebaseAuthProvider(this));

        mActivityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        mActivityRegisterBinding.setHandler(this);

        mActivityRegisterBinding.registerButton.setOnClickListener(view -> {
            String email = mActivityRegisterBinding.loginEditText.getText().toString().trim();
            String password = mActivityRegisterBinding.passwordEditText.getText().toString().trim();

            if (!email.isEmpty() && !password.isEmpty()) {
                mPresenter.signUpWithEmailAndPassword(email, password);
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
    public void onRegisterSuccess() {
        finish();
        Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegisterFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}

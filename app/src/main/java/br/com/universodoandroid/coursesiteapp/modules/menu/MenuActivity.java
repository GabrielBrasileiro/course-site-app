package br.com.universodoandroid.coursesiteapp.modules.menu;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.universodoandroid.coursesiteapp.R;
import br.com.universodoandroid.coursesiteapp.databinding.ActivityMenuBinding;
import br.com.universodoandroid.coursesiteapp.services.FirebaseDatabaseProvider;

public class MenuActivity extends AppCompatActivity implements MenuContract.View {

    private ActivityMenuBinding mActivityMenuBinding;
    private MenuContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new MenuPresenter(this, new FirebaseDatabaseProvider(this));

        mPresenter.checkUserSettings();

        mActivityMenuBinding = DataBindingUtil.setContentView(this, R.layout.activity_menu);
        mActivityMenuBinding.setHandler(this);
    }


    @Override
    public void setPresenter(MenuContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void dismissProgressBar() {

    }

    @Override
    public void setCorrectIntentByUserState(Intent intent, Class classReference) {
        if (classReference != this.getClass()) {
            startActivity(intent);
            finish();
        }
    }
}

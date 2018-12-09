package br.com.universodoandroid.coursesiteapp.modules.menu;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.annimon.stream.Optional;

import br.com.universodoandroid.coursesiteapp.R;
import br.com.universodoandroid.coursesiteapp.databinding.ActivityMenuBinding;
import br.com.universodoandroid.coursesiteapp.modules.menu.fragments.courses.CoursesFragment;
import br.com.universodoandroid.coursesiteapp.modules.menu.fragments.dashboard.DashboardFragment;
import br.com.universodoandroid.coursesiteapp.modules.menu.fragments.mycourses.MyCoursesFragment;
import br.com.universodoandroid.coursesiteapp.modules.menu.fragments.profile.ProfileFragment;
import br.com.universodoandroid.coursesiteapp.services.FirebaseDatabaseProvider;

public class MenuActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, MenuContract.View {

    private ActivityMenuBinding mActivityMenuBinding;
    private MenuContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new MenuPresenter(this, new FirebaseDatabaseProvider(this));

        mPresenter.checkUserSettings();

        mActivityMenuBinding = DataBindingUtil.setContentView(this, R.layout.activity_menu);
        mActivityMenuBinding.setHandler(this);

        mActivityMenuBinding.bottomNavigationView.setOnNavigationItemSelectedListener(this);
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

    @Override
    public void updateToolbarTitle(String name) {
        Optional.ofNullable(getSupportActionBar()).ifPresent(supportActionBar -> supportActionBar.setTitle(name));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_menu:
                fragment = DashboardFragment.newInstance();
                break;
            case R.id.navigation_my_courses:
                fragment = MyCoursesFragment.newInstance();
                break;
            case R.id.navigation_courses:
                fragment = CoursesFragment.newInstance();
                break;
            case R.id.navigation_profile:
                fragment = ProfileFragment.newInstance();
                break;
        }

        openFragment(fragment);

        return true;
    }

    private void openFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            ft.replace(R.id.container, fragment);
            ft.commit();
        }
    }

}

package br.com.universodoandroid.coursesiteapp.modules.menu.fragments.mycourses;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.universodoandroid.coursesiteapp.R;
import br.com.universodoandroid.coursesiteapp.databinding.FragmentMyCoursesBinding;
import br.com.universodoandroid.coursesiteapp.modules.menu.MenuActivity;

import static com.annimon.stream.Optional.ofNullable;

public class MyCoursesFragment extends Fragment implements MyCoursesContract.View {

    private FragmentMyCoursesBinding mFragmentMyCoursesBinding;

    public static Fragment newInstance() {
        return new MyCoursesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ofNullable(((MenuActivity) getActivity())).ifPresent(activity -> activity.updateToolbarTitle("My Courses"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentMyCoursesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_courses, container, false);
        mFragmentMyCoursesBinding.setHandler(this);


        return mFragmentMyCoursesBinding.getRoot();
    }

    @Override
    public void setPresenter(MyCoursesContract.Presenter presenter) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void dismissProgressBar() {

    }

}

package br.com.universodoandroid.coursesiteapp.modules.menu.fragments.courses;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.universodoandroid.coursesiteapp.R;
import br.com.universodoandroid.coursesiteapp.databinding.FragmentCoursesBinding;
import br.com.universodoandroid.coursesiteapp.modules.menu.MenuActivity;

import static com.annimon.stream.Optional.ofNullable;

public class CoursesFragment extends Fragment {

    private FragmentCoursesBinding mFragmentCoursesBinding;

    public static Fragment newInstance() {
        return new CoursesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ofNullable(((MenuActivity) getActivity())).ifPresent(activity -> activity.updateToolbarTitle("Courses"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentCoursesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_courses, container, false);
        mFragmentCoursesBinding.setHandler(this);

        return mFragmentCoursesBinding.getRoot();
    }

}

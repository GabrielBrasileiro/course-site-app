package br.com.universodoandroid.coursesiteapp.modules.menu.fragments.courses;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.universodoandroid.coursesiteapp.R;
import br.com.universodoandroid.coursesiteapp.databinding.FragmentCoursesBinding;
import br.com.universodoandroid.coursesiteapp.domain.Course;
import br.com.universodoandroid.coursesiteapp.modules.menu.MenuActivity;
import br.com.universodoandroid.coursesiteapp.modules.menu.fragments.courses.adapter.CourseAdapter;
import br.com.universodoandroid.coursesiteapp.modules.registercourse.NewCourseActivity;
import br.com.universodoandroid.coursesiteapp.services.FirebaseDatabaseCourseProvider;

import static com.annimon.stream.Optional.ofNullable;

public class CoursesFragment extends Fragment implements CoursesContract.View {

    private FragmentCoursesBinding mFragmentCoursesBinding;

    private CourseAdapter mCourseAdapter;
    private CoursesContract.Presenter mPresenter;

    public static Fragment newInstance() {
        return new CoursesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ofNullable(((MenuActivity) getActivity())).ifPresent(activity -> activity.updateToolbarTitle("Courses"));

        new CoursesPresenter(this, new FirebaseDatabaseCourseProvider(getActivity()));

        mCourseAdapter = new CourseAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentCoursesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_courses, container, false);
        mFragmentCoursesBinding.setHandler(this);

        mFragmentCoursesBinding.addNewScheduleButton.setOnClickListener(v ->
                getActivity().startActivity(new Intent(getActivity(), NewCourseActivity.class)));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = mFragmentCoursesBinding.schedulersRecyclerView;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(mCourseAdapter);

        mPresenter.requestSchedules();

        return mFragmentCoursesBinding.getRoot();
    }

    @Override
    public void loadCourses(List<Course> courses) {
        mCourseAdapter.clearData();
        mCourseAdapter.addAll(courses);
    }

    @Override
    public void setMessageEmptyVisibility(int visibility) {
        mFragmentCoursesBinding.emptyMessageTextView.setVisibility(visibility);
    }

    @Override
    public void setPresenter(CoursesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgressBar() {
        mFragmentCoursesBinding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgressBar() {
        mFragmentCoursesBinding.progressBar.setVisibility(View.GONE);
    }

}

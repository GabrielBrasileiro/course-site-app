package br.com.universodoandroid.coursesiteapp.modules.menu.fragments.courses;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import br.com.universodoandroid.coursesiteapp.domain.Course;
import br.com.universodoandroid.coursesiteapp.services.FirebaseDatabaseCourseProvider;
import br.com.universodoandroid.coursesiteapp.utils.RequestCallback;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class CoursesPresenter implements CoursesContract.Presenter {

    private CoursesContract.View mView;
    private FirebaseDatabaseCourseProvider mFirebaseDatabaseCourseProvider;

    public CoursesPresenter(@NonNull CoursesContract.View view,
                            @NonNull FirebaseDatabaseCourseProvider firebaseDatabaseCourseProvider) {
        mView = view;
        mFirebaseDatabaseCourseProvider = firebaseDatabaseCourseProvider;

        mView.setPresenter(this);
    }

    @Override
    public void requestSchedules() {
        mView.showProgressBar();
        mFirebaseDatabaseCourseProvider.provideCourses(new RequestCallback<List<Course>>() {
            @Override
            public void onSuccess(List<Course> courses) {
                if (!courses.isEmpty()) {
                    mView.loadCourses(courses);
                    mView.setMessageEmptyVisibility(GONE);
                } else {
                    mView.setMessageEmptyVisibility(VISIBLE);
                }
                mView.dismissProgressBar();
            }

            @Override
            public void onError(String errorMessage) {
                mView.setMessageEmptyVisibility(VISIBLE);
                Log.e(getClass().toString(), errorMessage);
            }
        });
    }

}

package br.com.universodoandroid.coursesiteapp.modules.registercourse;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.universodoandroid.coursesiteapp.domain.Categories;
import br.com.universodoandroid.coursesiteapp.domain.Student;
import br.com.universodoandroid.coursesiteapp.domain.Teacher;
import br.com.universodoandroid.coursesiteapp.services.FirebaseDatabaseCourseProvider;

public class NewCoursePresenter implements NewCourseContract.Presenter {

    private FirebaseDatabaseCourseProvider mFirebaseDatabaseCourseProvider;
    private NewCourseContract.View mView;

    public NewCoursePresenter(@NonNull NewCourseContract.View view,
                              @NonNull FirebaseDatabaseCourseProvider firebaseDatabaseCourseProvider) {
        mView = view;
        mFirebaseDatabaseCourseProvider = firebaseDatabaseCourseProvider;

        mView.setPresenter(this);
    }

    @Override
    public void registerCourse(String title, String value, List<Categories> categories, List<Teacher> teachers, List<Student> students) {
        mFirebaseDatabaseCourseProvider.registerCourseInformation(title, value, categories, teachers, students);
        mView.onFinishPost();
    }

}

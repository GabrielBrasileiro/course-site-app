package br.com.universodoandroid.coursesiteapp.modules.registercourse;

import java.util.List;

import br.com.universodoandroid.coursesiteapp.domain.Categories;
import br.com.universodoandroid.coursesiteapp.domain.Student;
import br.com.universodoandroid.coursesiteapp.domain.Teacher;
import br.com.universodoandroid.coursesiteapp.modules.BaseView;

public interface NewCourseContract {
    interface View extends BaseView<Presenter> {
        void onFinishPost();
    }

    interface Presenter {
        void registerCourse(String name, String value, List<Categories> categories, List<Teacher> teachers, List<Student> students);
    }
}

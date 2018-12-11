package br.com.universodoandroid.coursesiteapp.modules.menu.fragments.courses;

import java.util.List;

import br.com.universodoandroid.coursesiteapp.domain.Course;
import br.com.universodoandroid.coursesiteapp.modules.BaseView;

public interface CoursesContract {
    interface View extends BaseView<Presenter> {
        void loadCourses(List<Course> courses);
        void setMessageEmptyVisibility(int visibility);
    }

    interface Presenter {
        void requestSchedules();
    }
}

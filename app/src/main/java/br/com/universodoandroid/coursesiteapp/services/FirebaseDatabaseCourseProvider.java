package br.com.universodoandroid.coursesiteapp.services;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.universodoandroid.coursesiteapp.domain.Categories;
import br.com.universodoandroid.coursesiteapp.domain.Course;
import br.com.universodoandroid.coursesiteapp.domain.Student;
import br.com.universodoandroid.coursesiteapp.domain.Teacher;
import br.com.universodoandroid.coursesiteapp.services.base.FirebaseDatabaseProvider;
import br.com.universodoandroid.coursesiteapp.utils.RequestCallback;

import static br.com.universodoandroid.coursesiteapp.constants.Constants.KEY_COURSES_DATABASE_REFERENCE;
import static br.com.universodoandroid.coursesiteapp.constants.Constants.KEY_COURSE_CATEGORIES;
import static br.com.universodoandroid.coursesiteapp.constants.Constants.KEY_COURSE_NAME;
import static br.com.universodoandroid.coursesiteapp.constants.Constants.KEY_COURSE_STUDENTS;
import static br.com.universodoandroid.coursesiteapp.constants.Constants.KEY_COURSE_TEACHERS;
import static br.com.universodoandroid.coursesiteapp.constants.Constants.KEY_COURSE_VALUE;

public class FirebaseDatabaseCourseProvider extends FirebaseDatabaseProvider {

    public FirebaseDatabaseCourseProvider(Activity activity) {
        super(activity);
        setDatabaseReference(KEY_COURSES_DATABASE_REFERENCE);
    }

    public void registerCourseInformation(String name, String value, List<Categories> categories, List<Teacher> teachers, List<Student> students) {
        DatabaseReference databaseReference = getDatabaseReference().push();

        databaseReference.child(KEY_COURSE_NAME).setValue(name);
        databaseReference.child(KEY_COURSE_VALUE).setValue(value);
        databaseReference.child(KEY_COURSE_CATEGORIES).setValue(categories);
        databaseReference.child(KEY_COURSE_TEACHERS).setValue(teachers);
        databaseReference.child(KEY_COURSE_STUDENTS).setValue(students);
    }

    public void provideCourses(RequestCallback<List<Course>> callback) {
        getDatabaseReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Course> translations = new ArrayList<>();

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    translations.add(new Course(childDataSnapshot.getKey(),
                            (String) childDataSnapshot.child(KEY_COURSE_NAME).getValue(),
                            (String) childDataSnapshot.child(KEY_COURSE_VALUE).getValue(),
                            null, null, null
                    ));
                }

                callback.onSuccess(translations);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onError(databaseError.getMessage());
            }
        });
    }

}

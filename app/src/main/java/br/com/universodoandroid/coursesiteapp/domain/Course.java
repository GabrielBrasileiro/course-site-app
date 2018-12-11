package br.com.universodoandroid.coursesiteapp.domain;

import java.util.List;

public class Course {

    private String id;
    private String name;
    private String value;
    private List<Teacher> teachers;
    private List<Categories> categories;
    private List<Student> students;

    public Course(String id, String name, String value, List<Teacher> teachers, List<Categories> categories, List<Student> students) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.teachers = teachers;
        this.categories = categories;
        this.students = students;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public List<Student> getStudents() {
        return students;
    }
}

package br.com.universodoandroid.coursesiteapp.domain;

import android.net.Uri;

public class Student extends User {

    public Student(String name, String email, Uri photoUri) {
        super(name, email, photoUri);
    }

}

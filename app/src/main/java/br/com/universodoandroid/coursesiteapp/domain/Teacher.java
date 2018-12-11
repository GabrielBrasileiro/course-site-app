package br.com.universodoandroid.coursesiteapp.domain;

import android.net.Uri;

public class Teacher extends User {

    public Teacher(String name, String email, Uri photoUri) {
        super(name, email, photoUri);
    }

}

package br.com.universodoandroid.coursesiteapp.domain;

import android.net.Uri;

public class User {

    private String name;
    private String email;
    private Uri photoUrl;

    public User(String name, String email, Uri photoUri) {
        this.name = name;
        this.email = email;
        this.photoUrl = photoUri;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Uri getPhotoUrl() {
        return photoUrl;
    }

}

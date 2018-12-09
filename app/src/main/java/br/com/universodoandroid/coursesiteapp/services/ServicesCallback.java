package br.com.universodoandroid.coursesiteapp.services;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;

public interface ServicesCallback {
    void onSuccessResult(DataSnapshot dataSnapshot, FirebaseUser userUid);
    void onFailureResult(String errorMessage);
}

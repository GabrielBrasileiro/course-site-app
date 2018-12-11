package br.com.universodoandroid.coursesiteapp.services;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

public interface FirebaseDatabaseProviderContract {
    Activity getActivity();
    FirebaseAuth getAuthInstance();
}

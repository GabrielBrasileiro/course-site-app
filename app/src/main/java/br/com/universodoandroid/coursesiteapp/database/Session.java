package br.com.universodoandroid.coursesiteapp.database;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import static br.com.universodoandroid.coursesiteapp.constants.Constants.KEY_LOGGED_IN;
import static br.com.universodoandroid.coursesiteapp.constants.Constants.KEY_LOGGED_IN_DATABASE_REFERENCE;

public class Session {

    private SharedPreferences mSharedPreferencesSetup;
    private SharedPreferences.Editor mSharedPreferencesEditor;
    private Activity mActivity;

    public Session(Activity activity) {
        mActivity = activity;
        mSharedPreferencesSetup = getSharedPreferences();
        mSharedPreferencesEditor = mSharedPreferencesSetup.edit();
    }

    public void saveStateLoginSuccess() {
        mSharedPreferencesEditor.putBoolean(KEY_LOGGED_IN, true);
        mSharedPreferencesEditor.apply();
    }

    public void deleteStateLogin() {
        mSharedPreferencesEditor.putBoolean(KEY_LOGGED_IN, false);
        mSharedPreferencesEditor.clear();
        mSharedPreferencesEditor.apply();
    }

    public boolean isLoggedIn() {
        return mSharedPreferencesSetup.getBoolean(KEY_LOGGED_IN, false);
    }

    public Activity getActivity() {
        return mActivity;
    }

    private SharedPreferences getSharedPreferences() {
        return mActivity.getSharedPreferences(KEY_LOGGED_IN_DATABASE_REFERENCE, Context.MODE_PRIVATE);
    }

}

package br.com.universodoandroid.coursesiteapp.database;

import android.content.Context;
import android.content.SharedPreferences;

import static br.com.universodoandroid.coursesiteapp.constants.Constants.KEY_LOGGED_IN;
import static br.com.universodoandroid.coursesiteapp.constants.Constants.KEY_LOGGED_IN_DATABASE_REFERENCE;

public class Session {

    private SharedPreferences.Editor mSharedPreferenceEditor;
    private Context mContext;

    public Session(Context context){
        mContext = context;
        mSharedPreferenceEditor = getSharedPreferences().edit();
    }

    public void saveStateLogin(){
        mSharedPreferenceEditor.putBoolean(KEY_LOGGED_IN, true);
        mSharedPreferenceEditor.apply();
    }

    public void deleteStateLogin(){
        mSharedPreferenceEditor.putBoolean(KEY_LOGGED_IN, false);
        mSharedPreferenceEditor.clear();
        mSharedPreferenceEditor.apply();
    }

    private SharedPreferences getSharedPreferences() {
        return mContext.getSharedPreferences(KEY_LOGGED_IN_DATABASE_REFERENCE, Context.MODE_PRIVATE);
    }

}

package br.com.universodoandroid.coursesiteapp.services.persistence;

import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDatabasePersistence {

    private static FirebaseDatabase mDatabase;

    public static FirebaseDatabase getDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
        }

        return mDatabase;
    }

}

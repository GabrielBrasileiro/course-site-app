package br.com.universodoandroid.coursesiteapp.services.base;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import br.com.universodoandroid.coursesiteapp.services.FirebaseDatabaseProviderContract;
import br.com.universodoandroid.coursesiteapp.services.ServicesCallback;
import br.com.universodoandroid.coursesiteapp.services.persistence.FirebaseDatabasePersistence;

public class FirebaseDatabaseProvider implements FirebaseDatabaseProviderContract {

    private Activity mActivity;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;

    public FirebaseDatabaseProvider(Activity activity) {
        mActivity = activity;
        mAuth = FirebaseAuth.getInstance();
    }

    protected void initializeEventListener(DatabaseReference databaseReference, ServicesCallback callback) {
        if (mAuth.getCurrentUser() != null) {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    callback.onSuccessResult(dataSnapshot, mAuth.getCurrentUser());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    callback.onFailureResult(databaseError.getMessage());
                }
            });
        } else {
            callback.onFailureResult("Don't have any user");
        }
    }

    @Override
    public Activity getActivity() {
        return mActivity;
    }

    @Override
    public FirebaseAuth getAuthInstance() {
        return mAuth;
    }

    public void setDatabaseReference(String databaseReference) {
        mDatabaseReference = FirebaseDatabasePersistence.getDatabase().getReference().child(databaseReference);
        mDatabaseReference.keepSynced(true);
    }

    public DatabaseReference getDatabaseReference() {
        return mDatabaseReference;
    }

}

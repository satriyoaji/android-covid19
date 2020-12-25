package com.covidata.application.interactor;

import android.util.Log;

import androidx.annotation.NonNull;

import com.covidata.application.api_response.RegisterResponse;
import com.covidata.application.callback.AuthRequestCallback;
import com.covidata.application.callback.RequestCallback;
import com.covidata.application.contract.ProfileContract;
import com.covidata.application.model.User;
import com.covidata.application.util.SharedPreferencesUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class ProfileInteractor implements ProfileContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ProfileInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    public void requestSelfData(final RequestCallback<User> requestCallback) {
        db.collection("users")
            .document(getToken())
            .get()
            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        requestCallback.requestSuccess(document.toObject(User.class));
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
                }
            });
    }

    @Override
    public void updateProfile(String name, String email, String address, String note, RequestCallback<User> requestCallback) {
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("email", email);
        user.put("address", address);
        user.put("note", note);

        db.collection("users").document(getToken()).update(user);
    }

    @Override
    public void deleteToken() {
        sharedPreferencesUtil.clear();
    }

    private String getToken() {
        return sharedPreferencesUtil.getToken();
    }

}

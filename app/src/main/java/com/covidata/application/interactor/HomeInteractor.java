package com.covidata.application.interactor;

import android.util.Log;

import androidx.annotation.NonNull;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.covidata.application.api_response.LoginResponse;
import com.covidata.application.callback.RequestCallback;
import com.covidata.application.contract.HomeContract;
import com.covidata.application.model.User;
import com.covidata.application.util.BCrypt;
import com.covidata.application.util.SharedPreferencesUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import static android.content.ContentValues.TAG;

//import static androidx.constraintlayout.widget.StateSet.TAG;

public class HomeInteractor implements HomeContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public HomeInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public boolean isUserLogin() {
        return (sharedPreferencesUtil.getToken() != null);
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

    public String getToken() {
        return sharedPreferencesUtil.getToken();
    }

}

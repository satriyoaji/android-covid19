package com.covidata.application.interactor;

import android.util.Log;

import androidx.annotation.NonNull;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.covidata.application.api_response.LoginResponse;
import com.covidata.application.callback.RequestCallback;
import com.covidata.application.constant.ApiConstant;
import com.covidata.application.contract.LoginContract;
import com.covidata.application.util.BCrypt;
import com.covidata.application.util.SharedPreferencesUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.Document;

import java.util.List;

import static android.content.ContentValues.TAG;

public class LoginInteractor implements LoginContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public LoginInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestLogin(final String username, final String password, final RequestCallback<LoginResponse> requestCallback) {
        db.collection("users")
            .whereEqualTo("email", username)
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        List<DocumentSnapshot> docs = task.getResult().getDocuments();
                        boolean flag = false;
                        for (DocumentSnapshot document : task.getResult()) {
                            Log.d(TAG, "datas: " + document.getId() + " => " + document.getData());
                            for (DocumentSnapshot doc: docs) {
                                if(BCrypt.checkpw(password, (String) doc.get("password"))){
                                    flag = true;
                                }
                            }
                            if(flag)
                                requestCallback.requestSucceded(document.getId());
                            else
                                requestCallback.requestFailed("username or email incorrect");
                        }
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                }
            });
//        AndroidNetworking.post(ApiConstant.BASE_URL + "auth/login")
//                .addBodyParameter("email", username)
//                .addBodyParameter("password", password)
//                .build()
//                .getAsObject(LoginResponse.class, new ParsedRequestListener<LoginResponse>() {
//                    @Override
//                    public void onResponse(LoginResponse response) {
//                        if(response == null){
//                            requestCallback.requestFailed("Null Response");
//                        }
//                        else if(response.success){
////                            requestCallback.requestSuccess(response);
//                        }
//                        else {
//                            requestCallback.requestFailed(response.message);
//                        }
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        requestCallback.requestFailed(anError.getErrorDetail());
//                    }
//                });
    }

    @Override
    public void saveToken(String token) {
        sharedPreferencesUtil.setToken(token);
    }
}


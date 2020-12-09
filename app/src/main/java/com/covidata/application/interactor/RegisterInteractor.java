package com.covidata.application.interactor;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.covidata.application.api_response.RegisterResponse;
import com.covidata.application.callback.RequestCallback;
import com.covidata.application.constant.ApiConstant;
import com.covidata.application.contract.RegisterContract;
import com.covidata.application.util.SharedPreferencesUtil;
import com.google.android.gms.common.util.SharedPreferencesUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class RegisterInteractor implements RegisterContract.Interactor{
    private SharedPreferencesUtil sharedPreferencesUtil;

    public RegisterInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestRegister(FirebaseFirestore db, String name, String email, String password, String confirmPassword,
                                final RequestCallback<RegisterResponse> requestCallback) {

        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("email", email);
        user.put("password", password);
        user.put("address", null);
        user.put("note", null);

        db.collection("users")
            .add(user)
            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    requestCallback.requestSucceded(documentReference.getId());
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w(TAG, "Error adding document", e);
                    requestCallback.requestFailed(e.getMessage());
                }
            });

//        AndroidNetworking.post(ApiConstant.BASE_URL + "auth/register")
//                .addBodyParameter("name", name)
//                .addBodyParameter("email", email)
//                .addBodyParameter("password", password)
//                .addBodyParameter("password_confirmation", confirmPassword)
//                .addBodyParameter("role", "Pasien")
//                .build()
//                .getAsObject(RegisterResponse.class, new ParsedRequestListener<RegisterResponse>() {
//                    @Override
//                    public void onResponse(RegisterResponse response) {
//                        if(response == null){
//                            requestCallback.requestFailed("Null Response");
//                        }
//                        else if(response.success){
//                            requestCallback.requestSuccess(response);
//                        }
//                        else {
//                            requestCallback.requestFailed(response.message);
//                        }
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        requestCallback.requestFailed(anError.getErrorBody());
//                    }
//                });

    }

    @Override
    public void saveToken(String token) {
        sharedPreferencesUtil.setToken(token);
    }
}

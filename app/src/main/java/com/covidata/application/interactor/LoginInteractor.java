package com.covidata.application.interactor;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.covidata.application.api_response.LoginResponse;
import com.covidata.application.callback.RequestCallback;
import com.covidata.application.constant.ApiConstant;
import com.covidata.application.contract.LoginContract;
import com.covidata.application.util.SharedPreferencesUtil;

public class LoginInteractor implements LoginContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public LoginInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestLogin(String username, String password, final RequestCallback<LoginResponse> requestCallback) {
        AndroidNetworking.post(ApiConstant.BASE_URL + "auth/login")
                .addBodyParameter("email", username)
                .addBodyParameter("password", password)
                .build()
                .getAsObject(LoginResponse.class, new ParsedRequestListener<LoginResponse>() {
                    @Override
                    public void onResponse(LoginResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        }
                        else if(response.success){
//                            requestCallback.requestSuccess(response);
                        }
                        else {
                            requestCallback.requestFailed(response.message);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getErrorDetail());
                    }
                });
    }

    @Override
    public void saveToken(String token) {
        sharedPreferencesUtil.setToken(token);
    }
}


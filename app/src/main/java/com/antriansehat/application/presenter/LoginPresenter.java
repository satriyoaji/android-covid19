package com.antriansehat.application.presenter;

import android.util.Log;

import com.antriansehat.application.api_response.LoginResponse;
import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.contract.LoginContract;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private LoginContract.Interactor interactor;

    public LoginPresenter(LoginContract.View view, LoginContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void login(String username, String password) {
        view.startLoading();
        interactor.requestLogin(username, password, new RequestCallback<LoginResponse>() {
            @Override
            public void requestSuccess(LoginResponse data) {
                view.endLoading();
                view.loginSuccess(data.message);
                interactor.saveToken(data.access_token);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.loginFailed(errorMessage);
            }
        });
    }
}

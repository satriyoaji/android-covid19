package com.covidata.application.presenter;

import android.util.Log;
import android.widget.Toast;

import com.covidata.application.api_response.LoginResponse;
import com.covidata.application.api_response.RegisterResponse;
import com.covidata.application.callback.RequestCallback;
import com.covidata.application.contract.RegisterContract;

public class RegisterPresenter implements RegisterContract.Presenter{
    private RegisterContract.View view;
    private RegisterContract.Interactor interactor;

    public RegisterPresenter(RegisterContract.View view, RegisterContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void register(String name, String email, String password, String confirmPassword) {
        view.startLoading();

        if (password == confirmPassword){
            interactor.requestRegister(name, email, password, confirmPassword, new RequestCallback<RegisterResponse>() {
                @Override
                public void requestSuccess(RegisterResponse data) {
                    view.endLoading();
                    view.registerSuccess(data.message);
                    interactor.saveToken(data.access_token);
                }

                @Override
                public void requestFailed(String errorMessage) {
                    view.endLoading();
                    view.registerFailed(errorMessage);
                }
            });
        }else{
            view.passwordNotMatch();
        }

    }
}

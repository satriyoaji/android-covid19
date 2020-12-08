package com.covidata.application.contract;

import com.covidata.application.api_response.LoginResponse;
import com.covidata.application.api_response.RegisterResponse;
import com.covidata.application.callback.RequestCallback;

public interface RegisterContract {
    interface View {
        void startLoading();
        void endLoading();
        void registerSuccess(String message);
        void registerFailed(String message);
    }

    interface Presenter {
        void register(String name, String phone, String email, String password, String confirmPassword);
    }

    interface Interactor {
        void requestRegister(String name, String phone, String email, String password, String confirmPassword, RequestCallback<RegisterResponse> requestCallback);
        void saveToken(String token);
    }
}

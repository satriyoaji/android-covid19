package com.covidata.application.contract;

import com.covidata.application.api_response.LoginResponse;
import com.covidata.application.callback.AuthRequestCallback;
import com.covidata.application.callback.RequestCallback;
import com.google.firebase.firestore.FirebaseFirestore;

public interface LoginContract {
    interface View {
        void startLoading();
        void endLoading();
        void loginSuccess(String message);
        void loginFailed(String message);
    }

    interface Presenter {
        void login(String username, String password);
        String bcryptPassword(String password);
    }

    interface Interactor {
        void requestLogin(String username, String password, AuthRequestCallback<LoginResponse> requestCallback);
        void saveToken(String token);
    }
}

package com.covidata.application.contract;

import com.covidata.application.api_response.LoginResponse;
import com.covidata.application.api_response.RegisterResponse;
import com.covidata.application.callback.RequestCallback;
import com.google.firebase.firestore.FirebaseFirestore;

public interface RegisterContract {
    interface View {
        void startLoading();
        void endLoading();
        void passwordNotMatch();
        void registerSuccess(String message);
        void registerFailed(String message);
    }

    interface Presenter {
        void register(String name, String email, String password, String confirmPassword);
        String bcryptPassword(String password);
    }

    interface Interactor {
        void requestRegister(String name, String email, String password, String confirmPassword, RequestCallback<RegisterResponse> requestCallback);
        void saveToken(String token);
    }
}

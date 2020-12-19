package com.covidata.application.contract;

import com.covidata.application.api_response.LoginResponse;
import com.covidata.application.callback.RequestCallback;
import com.covidata.application.model.User;

import java.util.List;

public interface HomeContract {
    interface View {
        void startLoading();
        void endLoading();
        void whenUserLogin();
        void whenUserNotLogin();
        void showError(String errorMessage);
        void setSelfData(User user);
    }

    interface Presenter {
        void checkIsUserLogin();
        void requestGlobalData();
        void requestSelfData();
        void logout();
    }

    interface Interactor {
        boolean isUserLogin();
        void requestSelfData(RequestCallback<User> requestCallback);
    }

}

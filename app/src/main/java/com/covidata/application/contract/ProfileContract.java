package com.covidata.application.contract;

import com.covidata.application.api_response.NationalDataResponse;
import com.covidata.application.api_response.RegisterResponse;
import com.covidata.application.callback.AuthRequestCallback;
import com.covidata.application.callback.RequestCallback;
import com.covidata.application.model.NationalDataDetail;
import com.covidata.application.model.TotalData;
import com.covidata.application.model.User;

public interface ProfileContract {
    interface View {
        void showError(String errorMessage);
        void setSelfData(User user);
        void setUpdatedData(String message);
    }

    interface Presenter {
        void requestSelfData();
        void updateProfile(String name, String email, String address, String note);
        void logout();
    }

    interface Interactor {
        void requestSelfData(RequestCallback<User> requestCallback);
        void updateProfile(String name, String email, String address, String note, RequestCallback<User> requestCallback);
        void deleteToken();
    }
}

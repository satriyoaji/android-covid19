package com.covidata.application.presenter;


import android.util.Log;

import com.covidata.application.api_response.LoginResponse;
import com.covidata.application.api_response.NationalDataResponse;
import com.covidata.application.callback.RequestCallback;
import com.covidata.application.contract.HomeContract;
import com.covidata.application.model.User;

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View view;
    private HomeContract.Interactor interactor;

    public HomePresenter(HomeContract.View view, HomeContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void checkIsUserLogin() {
        if(interactor.isUserLogin()){
            view.whenUserLogin();
        }
        else {
            view.whenUserNotLogin();
        }
    }

    @Override
    public void requestNationalData() {
        interactor.requestNationalData(new RequestCallback<NationalDataResponse>() {
            @Override
            public void requestSuccess(NationalDataResponse data) {
                view.setTotalData(data.update.total);
                view.setUpdateData(data.update.penambahan);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }

    @Override
    public void requestSelfData() {
        interactor.requestSelfData(new RequestCallback<User>() {
            @Override
            public void requestSuccess(User user) {
                view.setSelfData(user);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }
}

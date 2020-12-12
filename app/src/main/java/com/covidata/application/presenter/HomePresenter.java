package com.covidata.application.presenter;


import com.covidata.application.contract.HomeContract;

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
    public void requestGlobalData() {

    }

    @Override
    public void logout() {

    }
}

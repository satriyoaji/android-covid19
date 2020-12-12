package com.covidata.application.contract;

import java.util.List;

public interface HomeContract {
    interface View {
        void startLoading();
        void endLoading();
        void whenUserLogin();
        void whenUserNotLogin();
        void showError(String errorMessage);
    }

    interface Presenter {
        void checkIsUserLogin();
        void requestGlobalData();
        void logout();
    }

    interface Interactor {
        boolean isUserLogin();
//        void requestGlobalData(RequestCallback<WaitingList> requestCallback);
        void logout();
    }

}

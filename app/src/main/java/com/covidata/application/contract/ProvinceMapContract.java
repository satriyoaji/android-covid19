package com.covidata.application.contract;

import com.covidata.application.callback.RequestCallback;
import com.covidata.application.model.ProvinceMap;

import java.util.List;

public interface ProvinceMapContract {
    interface View {
        void showProvinceData(List<ProvinceMap> listProvinceData);
        void showError(String errorMessage);
    }

    interface Presenter {
        boolean checkIsUserLogin();
        void requestDataCovidProvince();
    }

    interface Interactor {
        boolean isUserLogin();
        void requestDataCovidProvince(RequestCallback<List<ProvinceMap>> requestCallback);
    }
}

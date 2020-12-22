package com.covidata.application.presenter;

import com.covidata.application.callback.RequestCallback;
import com.covidata.application.contract.HomeContract;
import com.covidata.application.contract.ProvinceMapContract;
import com.covidata.application.model.Book;
import com.covidata.application.model.ProvinceMap;

import java.util.List;

public class ProvinceMapPresenter implements ProvinceMapContract.Presenter {
    private ProvinceMapContract.View view;
    private ProvinceMapContract.Interactor interactor;

    public ProvinceMapPresenter(ProvinceMapContract.View view, ProvinceMapContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public boolean checkIsUserLogin() {
        return interactor.isUserLogin();
    }

    @Override
    public void requestDataCovidProvince() {
        //view.startLoading();
        interactor.requestDataCovidProvince(new RequestCallback<List<ProvinceMap>>() {
            @Override
            public void requestSuccess(List<ProvinceMap> data) {
                //view.endLoading();
                view.showProvinceData(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                //view.endLoading();
                view.showError(errorMessage);
            }
        });
    }
}

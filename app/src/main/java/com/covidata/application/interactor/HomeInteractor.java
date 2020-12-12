package com.covidata.application.interactor;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.covidata.application.contract.HomeContract;
import com.covidata.application.util.SharedPreferencesUtil;

import java.util.List;

//import static androidx.constraintlayout.widget.StateSet.TAG;

public class HomeInteractor implements HomeContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public HomeInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public boolean isUserLogin() {
        return (sharedPreferencesUtil.getToken() != null);
    }

    @Override
    public void logout() {

    }
}

package com.covidata.application.interactor;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.covidata.application.api_response.NationalDataResponse;
import com.covidata.application.api_response.ProvinceMapResponse;
import com.covidata.application.callback.RequestCallback;
import com.covidata.application.contract.ProvinceMapContract;
import com.covidata.application.model.ProvinceMap;
import com.covidata.application.util.SharedPreferencesUtil;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ProvinceMapInteractor implements ProvinceMapContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ProvinceMapInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public boolean isUserLogin() {
        return (sharedPreferencesUtil.getToken() != null);
    }

    @Override
    public void requestDataCovidProvince(final RequestCallback<List<ProvinceMap>> requestCallback) {
        AndroidNetworking.get("https://data.covid19.go.id/public/api/prov.json")
                .build()
                .getAsObject(ProvinceMapResponse.class, new ParsedRequestListener<ProvinceMapResponse>() {
                    @Override
                    public void onResponse(ProvinceMapResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        }
                        else {
                            requestCallback.requestSuccess(response.list_data);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getErrorDetail());
                    }
                });
    }
}

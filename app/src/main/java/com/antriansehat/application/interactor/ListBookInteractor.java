package com.antriansehat.application.interactor;

import com.antriansehat.application.api_response.ListBookResponse;
import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.constant.ApiConstant;
import com.antriansehat.application.contract.ListBookContract;
import com.antriansehat.application.model.Book;
import com.antriansehat.application.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

public class ListBookInteractor implements ListBookContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public ListBookInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestListBook(final RequestCallback<List<Book>> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "books.php")
                .addHeaders("Authorization", sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(ListBookResponse.class, new ParsedRequestListener<ListBookResponse>() {
                    @Override
                    public void onResponse(ListBookResponse response) {
                        if(response != null){
                            requestCallback.requestSuccess(response.data);
                        }
                        else {
                            requestCallback.requestFailed("Null Response");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                    }
                });
    }

    @Override
    public void logout() {
        sharedPreferencesUtil.clear();
    }
}

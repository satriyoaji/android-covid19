package com.covidata.application.presenter;

import com.covidata.application.callback.RequestCallback;
import com.covidata.application.contract.ListBookContract;
import com.covidata.application.model.Book;

import java.util.List;

public class ListBookPresenter implements ListBookContract.Presenter {
    private ListBookContract.View view;
    private ListBookContract.Interactor interactor;

    public ListBookPresenter(ListBookContract.View view, ListBookContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void requestListBook() {
        view.startLoading();
        interactor.requestListBook(new RequestCallback<List<Book>>() {
//            @Override
//            public void requestSuccess(List<Book> data) {
//                view.endLoading();
//                view.showListBook(data);
//            }

            @Override
            public void requestSucceded(String docId) {

            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showError(errorMessage);
            }
        });
    }

    @Override
    public void logout() {
        interactor.logout();
    }
}

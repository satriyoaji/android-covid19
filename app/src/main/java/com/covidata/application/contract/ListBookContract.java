package com.covidata.application.contract;

import com.covidata.application.callback.RequestCallback;
import com.covidata.application.model.Book;

import java.util.List;

public interface ListBookContract {
    interface View {
        void startLoading();
        void endLoading();
        void showListBook(List<Book> books);
        void showError(String errorMessage);
    }

    interface Presenter {
        void requestListBook();
        void logout();
    }

    interface Interactor {
        void requestListBook(RequestCallback<List<Book>> requestCallback);
        void logout();
    }
}

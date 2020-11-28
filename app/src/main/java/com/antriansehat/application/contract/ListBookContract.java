package com.antriansehat.application.contract;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.model.Book;

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

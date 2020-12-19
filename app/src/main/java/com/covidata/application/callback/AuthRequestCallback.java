package com.covidata.application.callback;

public interface AuthRequestCallback<T> {
    void requestSuccess(String docId);
    void requestFailed(String errorMessage);
}

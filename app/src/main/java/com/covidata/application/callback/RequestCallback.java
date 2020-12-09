package com.covidata.application.callback;

public interface RequestCallback<T> {
//    void requestSuccess(T data);
    void requestSucceded(String docId);
    void requestFailed(String errorMessage);
}

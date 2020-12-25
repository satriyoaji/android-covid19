package com.covidata.application.presenter;

import com.covidata.application.callback.RequestCallback;
import com.covidata.application.contract.HomeContract;
import com.covidata.application.contract.ProfileContract;
import com.covidata.application.model.User;

public class ProfilePresenter implements ProfileContract.Presenter {
    private ProfileContract.View view;
    private ProfileContract.Interactor interactor;

    public ProfilePresenter(ProfileContract.View view, ProfileContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void requestSelfData() {
        interactor.requestSelfData(new RequestCallback<User>() {
            @Override
            public void requestSuccess(User user) {
                view.setSelfData(user);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }

    @Override
    public void updateProfile(String name, String email, String address, String note) {
        interactor.updateProfile(name, email, address, note, new RequestCallback<User>(){
            @Override
            public void requestSuccess(User data) {
                view.setUpdatedData("Anda berhasil mengedit profile");
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }

    @Override
    public void logout() {
        interactor.deleteToken();
    }
}

package com.covidata.application.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.covidata.application.R;
import com.covidata.application.contract.HomeContract;
import com.covidata.application.databinding.ActivityHomeBinding;
import com.covidata.application.interactor.HomeInteractor;
import com.covidata.application.presenter.HomePresenter;
import com.covidata.application.util.UtilProvider;

public class HomeActivity extends AppCompatActivity  implements HomeContract.View, View.OnClickListener{
    private HomeContract.Presenter presenter;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new HomePresenter(this, new HomeInteractor(UtilProvider.getSharedPreferencesUtil()));

        presenter.checkIsUserLogin();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void whenUserLogin() {
        presenter.requestGlobalData();
    }

    @Override
    public void whenUserNotLogin() {
        finish();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    @Override
    public void showError(String errorMessage) {

    }
}
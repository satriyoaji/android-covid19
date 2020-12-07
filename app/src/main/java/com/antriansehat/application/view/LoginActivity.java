package com.antriansehat.application.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.antriansehat.application.contract.LoginContract;
import com.antriansehat.application.databinding.ActivityLoginBinding;
import com.antriansehat.application.interactor.LoginInteractor;
import com.antriansehat.application.presenter.LoginPresenter;
import com.antriansehat.application.util.UtilProvider;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, View.OnClickListener {
    private LoginContract.Presenter presenter;
    private ActivityLoginBinding binding;
    private boolean isShowPassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new LoginPresenter(this, new LoginInteractor(UtilProvider.getSharedPreferencesUtil()));
        initView();
    }

    private void initView(){
        binding.btLogin.setOnClickListener(this);
        //binding.btShowPassword.setOnClickListener(this);
        //binding.linkedDaftar.setOnClickListener(this);
    }

    @Override
    public void startLoading() {
        //binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        //binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void loginSuccess(String message) {
        makeToast(message);
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFailed(String message) {
        makeToast(message);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.btLogin.getId()){
            onButtonLoginClick();
        }
    }

    private void onButtonShowPasswordClick() {
        if(!isShowPassword){
            binding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            isShowPassword = true;
        }else{
            binding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            isShowPassword = false;
        }
    }

    private void onLinkedDaftarClick() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void onButtonLoginClick(){
        presenter.login(binding.etEmail.getText().toString(), binding.etPassword.getText().toString());
    }

    private void makeToast(String message){
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }
}

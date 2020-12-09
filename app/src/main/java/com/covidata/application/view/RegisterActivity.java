package com.covidata.application.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.covidata.application.contract.RegisterContract;
import com.covidata.application.databinding.ActivityRegisterBinding;
import com.covidata.application.interactor.RegisterInteractor;
import com.covidata.application.presenter.RegisterPresenter;
import com.covidata.application.util.UtilProvider;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View, View.OnClickListener{
    private RegisterContract.Presenter presenter;
    private ActivityRegisterBinding binding;
    private boolean isShowPassword = false;
    private boolean isShowConfirmPassword = false;
//    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new RegisterPresenter(this, new RegisterInteractor(UtilProvider.getSharedPreferencesUtil()));
        initView();
    }

    private void initView(){
        binding.btRegister.setOnClickListener(this);
        //binding.btShowPassword.setOnClickListener(this);
        //binding.btShowConfirmPassword.setOnClickListener(this);
        binding.tvToLogin.setOnClickListener(this);
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
    public void registerSuccess(String message) {
        makeToast(message);
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void registerFailed(String message) {
        makeToast(message);
    }

    private void makeToast(String message){
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == binding.btRegister.getId()){
            onButtonRegisterClick();
        }else if(view.getId() == binding.tvToLogin.getId()){
            onLinkedMasukClick();
        }
    }

    private void onLinkedMasukClick() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
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

    private void onButtonShowConfirmPasswordClick() {
        if(!isShowConfirmPassword){
            binding.etConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            isShowConfirmPassword = true;
        }else{
            binding.etConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            isShowConfirmPassword = false;
        }
    }

    public void onButtonRegisterClick(){
        presenter.register(
                binding.etName.getText().toString(),
                "Phone",
                binding.etEmail.getText().toString(),
                binding.etPassword.getText().toString(),
                binding.etConfirmPassword.getText().toString()
        );
    }
}

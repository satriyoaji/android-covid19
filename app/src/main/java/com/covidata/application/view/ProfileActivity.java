package com.covidata.application.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.covidata.application.R;
import com.covidata.application.contract.ProfileContract;
import com.covidata.application.databinding.ActivityProfileBinding;
import com.covidata.application.interactor.ProfileInteractor;
import com.covidata.application.model.User;
import com.covidata.application.presenter.ProfilePresenter;
import com.covidata.application.util.UtilProvider;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity  implements ProfileContract.View, View.OnClickListener,
        BottomNavigationView.OnNavigationItemSelectedListener,
        BaseAuthenticatedView{

    private ActivityProfileBinding binding;
    private ProfileContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new ProfilePresenter(this, new ProfileInteractor(UtilProvider.getSharedPreferencesUtil()));

        initView();
    }

    private void initView(){
        presenter.requestSelfData();
        binding.btEdit.setOnClickListener(this);
        binding.btSave.setOnClickListener(this);
        binding.btLogout.setOnClickListener(this);
        binding.mapMenu.setOnClickListener(this);
        binding.bottomNav.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == binding.btLogout.getId())
            onLogoutAction();
        else if(view.getId() == binding.btEdit.getId())
            editProfile();
        else if(view.getId() == binding.btSave.getId())
            saveProfile();
        else if(view.getId() == binding.mapMenu.getId()){
            Intent time = new Intent(ProfileActivity.this, ProvinceMapsActivity.class);
            startActivity(time);
            finish();
        }
    }

    private void saveProfile() {
        presenter.updateProfile(
                binding.etName.getText().toString(),
                binding.etEmail.getText().toString(),
                binding.etAlamat.getText().toString(),
                binding.etNote.getText().toString()
        );
        binding.etName.setEnabled(false);
        binding.etEmail.setEnabled(false);
        binding.etAlamat.setEnabled(false);
        binding.etNote.setEnabled(false);
        binding.btSave.setVisibility(View.GONE);
        binding.btEdit.setVisibility(View.VISIBLE);
    }

    private void editProfile() {
        binding.etName.setEnabled(true);
        binding.etEmail.setEnabled(true);
        binding.etAlamat.setEnabled(true);
        binding.etNote.setEnabled(true);
        binding.btEdit.setVisibility(View.GONE);
        binding.btSave.setVisibility(View.VISIBLE);
    }

    private void onLogoutAction() {
        presenter.logout();
        Toast.makeText(getApplicationContext(), "You're logged out!",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        finishAffinity();
        startActivity(intent);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getApplicationContext(), errorMessage,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSelfData(User user) {
        binding.setUser(user);
    }

    @Override
    public void setUpdatedData(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bottomBarAction(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnHome:
                Intent home = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(home);
                finish();
                break;
            case R.id.btnUser:
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        bottomBarAction(item);
        return false;
    }
}

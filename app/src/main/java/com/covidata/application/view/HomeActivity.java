package com.covidata.application.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.covidata.application.R;
import com.covidata.application.contract.HomeContract;
import com.covidata.application.databinding.ActivityHomeBinding;
import com.covidata.application.interactor.HomeInteractor;
import com.covidata.application.model.TotalData;
import com.covidata.application.model.User;
import com.covidata.application.presenter.HomePresenter;
import com.covidata.application.util.UtilProvider;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class HomeActivity extends AppCompatActivity  implements HomeContract.View, View.OnClickListener,
        BottomNavigationView.OnNavigationItemSelectedListener,
        BaseAuthenticatedView{
    private HomeContract.Presenter presenter;
    private ActivityHomeBinding binding;
    private boolean isLoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new HomePresenter(this, new HomeInteractor(UtilProvider.getSharedPreferencesUtil()));

        presenter.checkIsUserLogin();
        initView();
    }

    private void initView() {
        if(isLoggedIn){
            getGreetings();
            presenter.requestSelfData();
            binding.bottomNav.setOnNavigationItemSelectedListener(this);
        }
//        binding.bottomNav.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == binding.mainFeature.getId()){
            Intent provincePage = new Intent(HomeActivity.this,ProvinceMapsActivity.class);
            startActivity(provincePage);
        }
    }

    @Override
    public void whenUserLogin() {
        isLoggedIn = true;
        presenter.requestNationalData();
    }

    @Override
    public void whenUserNotLogin() {
        finish();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getApplicationContext(), errorMessage,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSelfData(User user) {
        binding.tvName.setText(user.getName());
        binding.tvNote.setText(user.getNote());
    }

    @Override
    public void setUpdateData(TotalData update) {
        binding.tvUpdated.setText("Pembaruan terakhir pada  "+update.getCreated());
        binding.tvTambahSembuh.setText("Penambahan: + "+update.getJumlah_sembuh());
        binding.tvTambahPositif.setText("Penambahan: + "+update.getJumlah_positif());
        binding.tvTambahPerawatan.setText("Penambahan: + "+update.getJumlah_dirawat());
        binding.tvTambahMeninggal.setText("Penambahan: + "+update.getJumlah_meninggal());
    }

    @Override
    public void setTotalData(TotalData total) {
        binding.tvNilaiPositif.setText(String.valueOf(total.getJumlah_positif()));
        binding.tvNilaiSembuh.setText(String.valueOf(total.getJumlah_sembuh()));
        binding.tvNilaiMeninggal.setText(String.valueOf(total.getJumlah_meninggal()));
        binding.tvNilaiPerawatan.setText(String.valueOf(total.getJumlah_dirawat()));
    }

    private void getGreetings(){
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        System.out.println("waktu "+ timeOfDay);
        if(timeOfDay >= 0 && timeOfDay < 12){
            binding.tvWelcome.setText("Selamat Pagi");
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            binding.tvWelcome.setText("Selamat Siang");
        }else if(timeOfDay >= 16 && timeOfDay < 20){
            binding.tvWelcome.setText("Selamat Sore");
        }else if(timeOfDay >= 20 && timeOfDay < 24){
            binding.tvWelcome.setText("Selamat Malam");
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        bottomBarAction(item);
        return false;
    }

    @Override
    public void bottomBarAction(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnHome:
                break;
            case R.id.btnUser:
                break;
        }
    }
}
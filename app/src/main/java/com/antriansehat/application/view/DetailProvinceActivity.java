package com.antriansehat.application.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.antriansehat.application.databinding.ActivityDetailProvinceBinding;

public class DetailProvinceActivity extends AppCompatActivity {
    private ActivityDetailProvinceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailProvinceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView(){

    }
}

package com.covidata.application.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.covidata.application.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {
    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView(){

    }
}

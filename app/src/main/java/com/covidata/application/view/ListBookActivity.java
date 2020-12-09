package com.covidata.application.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.covidata.application.contract.ListBookContract;
import com.covidata.application.databinding.ActivityListBookBinding;
import com.covidata.application.interactor.ListBookInteractor;
import com.covidata.application.model.Book;
import com.covidata.application.presenter.ListBookPresenter;
import com.covidata.application.util.UtilProvider;

import java.util.List;

public class ListBookActivity extends AppCompatActivity implements ListBookContract.View, View.OnClickListener {
    private ActivityListBookBinding binding;
    private ListBookPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new ListBookPresenter(this, new ListBookInteractor(UtilProvider.getSharedPreferencesUtil()));

        initView();
        presenter.requestListBook();
    }

    private void initView(){
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.logoutButton.setOnClickListener(this);
    }

    @Override
    public void startLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void endLoading() {
        binding.progressBar.setVisibility(View.GONE);
        binding.recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showListBook(List<Book> books) {
//        binding.recyclerView.setAdapter(new ListBookAdapter(books, getLayoutInflater()));
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.logoutButton.getId()){
            onLogoutButtonClick();
        }
    }

    private void onLogoutButtonClick(){
        presenter.logout();
        finish();
//        startActivity(new Intent(this, LoginActivity.class));
    }
}

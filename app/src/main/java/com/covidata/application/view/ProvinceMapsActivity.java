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
import com.covidata.application.contract.ProvinceMapContract;
import com.covidata.application.databinding.ActivityProvinceMapsBinding;
import com.covidata.application.interactor.ProvinceMapInteractor;
import com.covidata.application.model.ProvinceMap;
import com.covidata.application.presenter.ProvinceMapPresenter;
import com.covidata.application.util.UtilProvider;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ProvinceMapsActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        ProvinceMapContract.View,
        View.OnClickListener,
        BottomNavigationView.OnNavigationItemSelectedListener,
        BaseAuthenticatedView{

    private GoogleMap mMap;
    private ProvinceMapContract.Presenter presenter;
    private ActivityProvinceMapsBinding binding;
    private List<ProvinceMap> listProvinceData = new ArrayList<ProvinceMap>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProvinceMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.cvDetailInfo.setVisibility(View.GONE);
        presenter = new ProvinceMapPresenter(this, new ProvinceMapInteractor(UtilProvider.getSharedPreferencesUtil()));

        if(!presenter.checkIsUserLogin()){
            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

        presenter.requestDataCovidProvince();
        binding.bottomNav.setOnNavigationItemSelectedListener(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.province_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        float zoomLevel = 5f;
        mMap = googleMap;

        LatLng defaultLatLng = new LatLng(	-6.200000, 106.816666);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLatLng, zoomLevel));

        if(!this.listProvinceData.isEmpty()){
            for(final ProvinceMap provinceData : listProvinceData){
                LatLng latLngData = new LatLng(provinceData.getLokasi().getLat(), provinceData.getLokasi().getLon());
                mMap.addMarker(new MarkerOptions().position(latLngData)
                        .title(provinceData.getKey()));
                mMap.setOnMarkerClickListener(this);
            }
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        binding.cvDetailInfo.setVisibility(View.VISIBLE);
        String markerId = marker.getId();
        String[] splitMarkerId = markerId.split("m");

        binding.tvTitle.setText("DATA TERBARU DI PROVINSI " + listProvinceData.get(Integer.parseInt(splitMarkerId[1])).getKey());
        binding.tvJumlahPositif.setText(listProvinceData.get(Integer.parseInt(splitMarkerId[1])).getJumlah_kasus());
        binding.tvJumlahSembuh.setText(listProvinceData.get(Integer.parseInt(splitMarkerId[1])).getJumlah_sembuh());
        binding.tvJumlahMeninggal.setText(listProvinceData.get(Integer.parseInt(splitMarkerId[1])).getJumlah_meninggal());
        binding.tvJumlahPerawatan.setText(listProvinceData.get(Integer.parseInt(splitMarkerId[1])).getJumlah_dirawat());

        return false;
    }

    @Override
    public void showProvinceData(List<ProvinceMap> listProvinceData) {
        this.listProvinceData = listProvinceData;
        this.onMapReady(mMap);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {

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
                Intent home = new Intent(ProvinceMapsActivity.this, HomeActivity.class);
                startActivity(home);
                finish();
                break;
            case R.id.btnUser:
                Intent profile = new Intent(ProvinceMapsActivity.this, ProfileActivity.class);
                startActivity(profile);
                finish();
                break;
        }
    }
}
package com.covidata.application;

import android.app.Application;

import com.antriansehat.application.util.UtilProvider;
import com.androidnetworking.AndroidNetworking;

// Set this class to android:name in AndroidManifest.xml at application tag
public class Starter extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        AndroidNetworking.initialize(getApplicationContext());
        UtilProvider.initialize(getApplicationContext());
    }
}

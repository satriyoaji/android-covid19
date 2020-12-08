package com.covidata.application.view;

import android.view.MenuItem;

import androidx.annotation.NonNull;

public interface BaseAuthenticatedView {
    void bottomBarAction(@NonNull MenuItem item);
}

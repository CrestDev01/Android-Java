package com.example.placevisited.app;

import android.app.Application;

import com.example.placevisited.home.data.AppDatabase;

public class PlacesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppDatabase.getInstance(this);
    }
}

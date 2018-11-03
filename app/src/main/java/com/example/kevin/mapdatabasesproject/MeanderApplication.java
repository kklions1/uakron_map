package com.example.kevin.mapdatabasesproject;

import android.app.Application;

import com.example.kevin.mapdatabasesproject.database.DatabaseHelper;

public class MeanderApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHelper.setInstance(this);
    }
}

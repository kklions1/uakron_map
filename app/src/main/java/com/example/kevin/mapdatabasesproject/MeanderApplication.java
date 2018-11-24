package com.example.kevin.mapdatabasesproject;

import android.app.Application;

import com.example.kevin.mapdatabasesproject.database.DatabaseHelper;

/**
 * Application class, in this case just simply creates an instance of the database when the application is started
 */
public class MeanderApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHelper.setInstance(this);
    }
}

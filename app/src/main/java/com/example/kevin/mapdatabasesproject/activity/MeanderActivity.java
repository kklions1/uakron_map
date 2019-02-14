package com.example.kevin.mapdatabasesproject.activity;


import android.app.Activity;
import android.os.Bundle;

import com.example.kevin.mapdatabasesproject.fragment.CourseDetailsFragment;
import com.example.kevin.mapdatabasesproject.fragment.LoginFragment;
import com.example.kevin.mapdatabasesproject.fragment.MapFragment;
import com.example.kevin.mapdatabasesproject.fragment.ScheduleFragment;

/**
 * Base Activity class for the application
 */
public class MeanderActivity extends Activity implements
        CourseDetailsFragment.CourseDetailsNavigationListener, LoginFragment.LoginNavigationListener,
        MapFragment.MapNavigationListener, ScheduleFragment.ScheduleNavigationListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void navigateToCourseDetails() {

    }

    @Override
    public void navigateToLogin() {

    }

    @Override
    public void navigateToMap() {

    }

    @Override
    public void navigateToSchedule() {

    }
}

package com.example.kevin.mapdatabasesproject.activity;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.kevin.mapdatabasesproject.R;
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
        setContentView(R.layout.fragment_holder);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_layout_holder, new LoginFragment());
        transaction.commit();
    }

    @Override
    public void navigateToCourseDetails() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_layout_holder, new CourseDetailsFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void navigateToLogin() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_layout_holder, new LoginFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void navigateToMap() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_layout_holder, new MapFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void navigateToSchedule() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_layout_holder, new ScheduleFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

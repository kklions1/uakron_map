package com.example.kevin.mapdatabasesproject.activity;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kevin.mapdatabasesproject.R;
import com.example.kevin.mapdatabasesproject.fragment.CourseDetailsFragment;
import com.example.kevin.mapdatabasesproject.fragment.LoginFragment;
import com.example.kevin.mapdatabasesproject.fragment.MapFragment;
import com.example.kevin.mapdatabasesproject.fragment.ScheduleFragment;

/**
 * Base Activity class for the application
 */
public class MeanderActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_holder);

        SharedPreferences sharedPreferences = this.getSharedPreferences(getString(R.string.shared_preferences_key),
                Context.MODE_PRIVATE);
        if (sharedPreferences.getAll().isEmpty()) {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.fragment_layout_holder, new LoginFragment());
            transaction.commit();
        } else {
            navigateToMap();
        }
    }



    public void navigateToMap() {
//        Intent intent = new Intent(MeanderActivity.this, MapsActivity.class);
        Intent intent = new Intent(MeanderActivity.this, MapsActivityKt.class);
        startActivity(intent);
    }
}

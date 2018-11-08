package com.example.kevin.mapdatabasesproject.activity;

import android.app.Activity;
import android.os.Bundle;


import com.example.kevin.mapdatabasesproject.R;

/**
 * This screen will handle updating, deleting, and additions of a new course
 */
public class CourseDetailsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_details_layout);
    }
}


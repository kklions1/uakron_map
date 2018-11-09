package com.example.kevin.mapdatabasesproject.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;


import com.example.kevin.mapdatabasesproject.R;
import com.example.kevin.mapdatabasesproject.database.dao.CourseDAO;

/**
 * This screen will handle updating, deleting, and additions of a new course
 */
public class CourseDetailsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_details_layout);

        Button saveBtn = findViewById(R.id.continue_btn);
        saveBtn.setOnClickListener((view) -> {
            CourseDAO dao = new CourseDAO();
//            dao.
        });

    }
}


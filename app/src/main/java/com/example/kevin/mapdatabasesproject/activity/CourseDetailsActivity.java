package com.example.kevin.mapdatabasesproject.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


import com.example.kevin.mapdatabasesproject.R;
import com.example.kevin.mapdatabasesproject.database.dao.CourseDAO;
import com.example.kevin.mapdatabasesproject.model.Course;

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

            EditText courseName = findViewById(R.id.course_name_edit_text);
            EditText courseLocation = findViewById(R.id.course_location_edit_text);

            CourseDAO dao = new CourseDAO();

            dao.save(new Course.Builder()
                    .setName(courseName.getText().toString())
                    .setLocationId(12)
                    .setStartTimeHour(2)
                    .setStartTimeMinute(30)
                    .setEndTimeHour(4)
                    .setEndTimeMinute(20)
                    .build());



        });

    }
}


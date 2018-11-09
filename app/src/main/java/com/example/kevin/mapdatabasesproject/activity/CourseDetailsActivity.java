package com.example.kevin.mapdatabasesproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


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
            Spinner courseLocation = findViewById(R.id.course_location_spinner);

            CourseDAO dao = new CourseDAO();

            dao.save(new Course.Builder()
                    .setName(courseName.getText().toString())
                    .setLocationId(12)
                    .setLocationName(String.valueOf(courseLocation.getSelectedItem()))
                    .setStartTimeHour(2)
                    .setStartTimeMinute(30)
                    .setEndTimeHour(4)
                    .setEndTimeMinute(20)
                    .build());

            // Once the item is saved, navigate to schedule screen
            navigateToScheduleScreen();

        });
    }

    private void navigateToScheduleScreen() {
        Intent intent = new Intent(CourseDetailsActivity.this, ScheduleActivity.class);
        startActivity(intent);
    }
}


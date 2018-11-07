package com.example.kevin.mapdatabasesproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.kevin.mapdatabasesproject.model.Course;
import com.example.kevin.mapdatabasesproject.R;
import com.example.kevin.mapdatabasesproject.adapter.ScheduleAdapter;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_layout);

        List<Course> mockCourseData = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mockCourseData.add(new Course("Mock Course Data", "Mock Course Time", Integer.toString(i)));
        }

        RecyclerView scheduleRecyclerView = findViewById(R.id.schedule_recycler_view);
        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(mockCourseData,(view, position) -> {
            // TODO make this do something eventually
            Toast.makeText(this, "Click on item: " + position, Toast.LENGTH_SHORT).show();
        });

        scheduleRecyclerView.setAdapter(scheduleAdapter);
        scheduleRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton addCourseFab = findViewById(R.id.add_course_fab);
        addCourseFab.setOnClickListener((view) -> navigateToCourseDetails());
    }

    private void navigateToCourseDetails() {
        Intent intent = new Intent(ScheduleActivity.this, CourseDetailsActivity.class);
        startActivity(intent);
    }
}

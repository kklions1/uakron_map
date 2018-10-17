package com.example.kevin.mapdatabasesproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_layout);

        List<Course> mockCourseData = new ArrayList<>();
        mockCourseData.add(new Course("Database Management", "4:15", "12"));
        mockCourseData.add(new Course("Parallel Processing", "11:45", "401400"));
        mockCourseData.add(new Course("Intro to Ethics", "11:15", "101"));

        RecyclerView scheduleRecyclerView = findViewById(R.id.schedule_recycler_view);
        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(mockCourseData);

        scheduleRecyclerView.setAdapter(scheduleAdapter);
        scheduleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

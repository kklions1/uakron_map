package com.example.kevin.mapdatabasesproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.kevin.mapdatabasesproject.database.dao.CourseDAO;
import com.example.kevin.mapdatabasesproject.model.Course;
import com.example.kevin.mapdatabasesproject.R;
import com.example.kevin.mapdatabasesproject.adapter.ScheduleAdapter;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends Activity {

    private CourseDAO courseDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_layout);

        // TODO this should be onResume(), but because the schedule adapter and instantiating a listener is tightly coupled, it can't
        courseDAO = new CourseDAO();

        List<Course> mockCourseData = new ArrayList<>(courseDAO.getAll());

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

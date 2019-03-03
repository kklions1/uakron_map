package com.example.kevin.mapdatabasesproject.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.kevin.mapdatabasesproject.database.dao.CourseDAO;
import com.example.kevin.mapdatabasesproject.manager.MeanderDataManager;
import com.example.kevin.mapdatabasesproject.model.Course;
import com.example.kevin.mapdatabasesproject.R;
import com.example.kevin.mapdatabasesproject.adapter.ScheduleAdapter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ScheduleActivity extends Activity {
    private CourseDAO courseDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_layout);

        courseDAO = new CourseDAO();
        List<Course> courseData = new ArrayList<>(courseDAO.getAll());

        if (courseData.isEmpty()) {
            Toast.makeText(this, "No local data found, please refresh", Toast.LENGTH_LONG).show();
        }

        RecyclerView scheduleRecyclerView = findViewById(R.id.schedule_recycler_view);
        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(courseData,(view, position) -> {
            // this list is used to fix a crash when trying to click a class after deleting another one
            List<Course> data = courseDAO.getAll();
            new AlertDialog.Builder(this)
                    .setTitle(data.get(position).getName())
                    .setPositiveButton("Update", (dialogInterface, id) -> {
                        Course selected = data.get(position);
                        navigateToCourseDetails(selected);
                        dialogInterface.cancel();
                    })
                    .setNegativeButton("Delete", (dialogInterface, id) -> {
                        courseDAO.delete(data.get(position).getCourseId());
                        // recreate basically restarts the activity its called on.
                        refreshScreen();
                        dialogInterface.cancel();
                    })
                    .setNeutralButton("Cancel", (dialogInterface, id) -> dialogInterface.cancel())
                    .show();
        });

        scheduleRecyclerView.setAdapter(scheduleAdapter);
        scheduleRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton addCourseFab = findViewById(R.id.add_course_fab);
        addCourseFab.setOnClickListener((view) -> navigateToCourseDetails(null));

        FloatingActionButton refreshCourseList = findViewById(R.id.refresh_course_list);
        refreshCourseList.setOnClickListener((view) -> {
            RefreshCall thread = new RefreshCall();
            thread.execute();
        });
    }

    @Override
    public void onBackPressed() {
        navigateToMap();
    }

    private void navigateToCourseDetails(Course courseData) {
        Intent intent = new Intent(ScheduleActivity.this, CourseDetailsActivity.class);
        intent.putExtra("Course Data", courseData);
        startActivity(intent);
    }

    private void navigateToMap() {
        Intent intent = new Intent(ScheduleActivity.this, MapsActivity.class);
        startActivity(intent);
    }

    private void refreshScreen() {
        // this definitely feels hacky, but it works
        this.recreate();
    }

    public class RefreshCall extends AsyncTask<Void, Void, String> {
        private final String appURL = "https://secure-outpost-229516.appspot.com/";

        @Override
        public String doInBackground(Void... uselessArgs) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(appURL)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }
        }

        @Override
        public void onPostExecute(String result){
            List<Course> resultCourseList = new MeanderDataManager().parseJsonResponse(result);
            CourseDAO dao = new CourseDAO();
            dao.clear();

            for (Course c : resultCourseList) {
                dao.save(c);
            }
            refreshScreen();
        }
    }
}

package com.example.kevin.mapdatabasesproject.activity;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.kevin.mapdatabasesproject.R;
import com.example.kevin.mapdatabasesproject.database.dao.CourseDAO;
import com.example.kevin.mapdatabasesproject.fragment.TimePickerFragment;
import com.example.kevin.mapdatabasesproject.model.Course;

/**
 * This screen will handle updating, deleting, and additions of a new course
 */
public class CourseDetailsActivity extends Activity implements TimePickerDialog.OnTimeSetListener {
    private int startTimeHour;
    private int startTimeMinute;
    private int endTimeHour;
    private int endTimeMinute;
    private boolean isStartTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_details_layout);

        final Course updateCourse = (Course) getIntent().getSerializableExtra("Course Data");
        if (updateCourse != null) {
            initializeWithDefaultValues(updateCourse);
        } else {
            initializeWithNoDefaultValues();
        }

        Button startTimeButton = findViewById(R.id.start_time_btn);
        Button endTimeButton = findViewById(R.id.end_time_btn);

        startTimeButton.setOnClickListener((view) -> createTimePickerDialog(true));
        endTimeButton.setOnClickListener((view) -> createTimePickerDialog(false));
    }

    private void navigateToScheduleScreen() {
        Intent intent = new Intent(CourseDetailsActivity.this, ScheduleActivity.class);
        startActivity(intent);
    }

    // isStartTime is a flag that will determine where the values for the timepicker are to be stored
    private void createTimePickerDialog(boolean isStartTime) {
        this.isStartTime = isStartTime;
        DialogFragment dialog = new TimePickerFragment();
        dialog.show(getFragmentManager(), "TimePicker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (isStartTime) {
            this.startTimeHour = hourOfDay;
            this.startTimeMinute = minute;

            TextView startTimeDisplay = findViewById(R.id.start_time_display);
            startTimeDisplay.setText(Integer.toString(hourOfDay) + ":" + Integer.toString(minute));
        } else {
            this.endTimeHour = hourOfDay;
            this.endTimeMinute = minute;

            TextView endTimeDisplay = findViewById(R.id.end_time_display);
            endTimeDisplay.setText(Integer.toString(hourOfDay) + ":" + Integer.toString(minute));
        }
    }

    // Used primary to change the behavior of the Save/Update button
    private void initializeWithNoDefaultValues() {
        Button saveBtn = findViewById(R.id.continue_btn);
        saveBtn.setOnClickListener((view) -> {

            EditText courseName = findViewById(R.id.course_name_edit_text);
            Spinner courseLocation = findViewById(R.id.course_location_spinner);

            CourseDAO dao = new CourseDAO();

            dao.save(new Course.Builder()
                    .setName(courseName.getText().toString())
                    .setLocationId(12)
                    .setLocationName(String.valueOf(courseLocation.getSelectedItem()))
                    .setStartTimeHour(this.startTimeHour)
                    .setStartTimeMinute(this.startTimeMinute)
                    .setEndTimeHour(this.endTimeHour)
                    .setEndTimeMinute(this.endTimeMinute)
                    .build());

            // Once the item is saved, navigate to schedule screen
            navigateToScheduleScreen();
        });
    }

    private void initializeWithDefaultValues(Course course) {
        EditText courseName = findViewById(R.id.course_name_edit_text);
        courseName.setText(course.getName());

        startTimeHour = course.getStartTimeHour();
        startTimeMinute = course.getStartTimeMinute();
        endTimeHour = course.getEndTimeHour();
        endTimeMinute = course.getEndTimeMinute();

        TextView startTimeDisplay = findViewById(R.id.start_time_display);
        startTimeDisplay.setText(Integer.toString(startTimeHour) + ":" + Integer.toString(startTimeMinute));

        TextView endTimeDisplay = findViewById(R.id.end_time_display);
        endTimeDisplay.setText(Integer.toString(endTimeHour) + ":" + Integer.toString(endTimeMinute));

        Spinner courseLocation = findViewById(R.id.course_location_spinner);

        Button updateButton = findViewById(R.id.continue_btn);
        updateButton.setOnClickListener((view) -> {
            CourseDAO dao = new CourseDAO();
            dao.update(new Course.Builder()
                    .setName(courseName.getText().toString())
                    .setCourseId(course.getCourseId()) // preserve the course id, this is being passed in so the update method can update properly
                    .setLocationId(course.getLocationId()) // TODO make this actually do something
                    .setStartTimeHour(startTimeHour)
                    .setStartTimeMinute(startTimeMinute)
                    .setEndTimeHour(endTimeHour)
                    .setEndTimeMinute(endTimeMinute)
                    .setLocationName(String.valueOf(courseLocation.getSelectedItem()))
                    .build());
            navigateToScheduleScreen();
        });
        updateButton.setText("Update Entry");
    }
}


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

        Button startTimeButton = findViewById(R.id.start_time_btn);
        Button endTimeButton = findViewById(R.id.end_time_btn);

        startTimeButton.setOnClickListener((view) -> createTimePickerDialog(true));
        endTimeButton.setOnClickListener((view) -> createTimePickerDialog(false));

        Button saveBtn = findViewById(R.id.continue_btn);
        saveBtn.setOnClickListener((view) -> {

            EditText courseName = findViewById(R.id.course_name_edit_text);
            Spinner courseLocation = findViewById(R.id.course_location_spinner);

            CourseDAO dao = new CourseDAO();

            dao.save(new Course.Builder()
                    .setName(courseName.getText().toString())
                    .setCourseId(dao.getCount() + 1)
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
}


package com.example.kevin.mapdatabasesproject.fragment;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.kevin.mapdatabasesproject.R;
import com.example.kevin.mapdatabasesproject.activity.CourseDetailsActivity;
import com.example.kevin.mapdatabasesproject.activity.ScheduleActivity;
import com.example.kevin.mapdatabasesproject.database.dao.CourseDAO;
import com.example.kevin.mapdatabasesproject.model.Course;
import com.example.kevin.mapdatabasesproject.model.CourseTime;

public class CourseDetailsFragment extends Fragment implements TimePickerDialog.OnTimeSetListener {
    private CourseTime startTime;
    private CourseTime endTime;
    private boolean isStartTime;
    private View fragmentView;

    // TODO this method needs an argument
    public static CourseDetailsFragment newInstance(Course updateCourse) {
        CourseDetailsFragment fragment = new CourseDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Course Data", updateCourse);

        return new CourseDetailsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Course updateCourse = (Course) savedInstanceState.getSerializable("Course Data");
        fragmentView = inflater.inflate(R.layout.course_details_layout, container, false);
        if (updateCourse != null) {
            initializeWithDefaultValues(updateCourse);
        } else {
            initializeWithNoDefaultValues();
        }

        Button startTimeButton = fragmentView.findViewById(R.id.start_time_btn);
        Button endTimeButton = fragmentView.findViewById(R.id.end_time_btn);

        startTime = new CourseTime();
        endTime = new CourseTime();

        startTimeButton.setOnClickListener((view) -> createTimePickerDialog(true));
        endTimeButton.setOnClickListener((view) -> createTimePickerDialog(false));
        return fragmentView;
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
            this.startTime.setHour(hourOfDay);
            this.startTime.setMinute(minute);

            TextView startTimeDisplay = fragmentView.findViewById(R.id.start_time_display);
            startTimeDisplay.setText(startTime.toString());
        } else {
            this.endTime.setHour(hourOfDay);
            this.endTime.setMinute(minute);

            TextView endTimeDisplay = fragmentView.findViewById(R.id.end_time_display);
            endTimeDisplay.setText(endTime.toString());
        }
    }

    // Used primary to change the behavior of the Save/Update button
    private void initializeWithNoDefaultValues() {
        Button saveBtn = fragmentView.findViewById(R.id.continue_btn);

        saveBtn.setOnClickListener((view) -> {

            EditText courseName = fragmentView.findViewById(R.id.course_name_edit_text);
            Spinner courseLocation = fragmentView.findViewById(R.id.course_location_spinner);

            String selectedLocation = courseLocation.getSelectedItem().toString();

            CheckBox mondayBox = fragmentView.findViewById(R.id.monday_checkbox);
            CheckBox tuesdayBox = fragmentView.findViewById(R.id.tuesday_checkbox);
            CheckBox wednesdayBox = fragmentView.findViewById(R.id.its_wednesday_my_dudes);
            CheckBox thursdayBox = fragmentView.findViewById(R.id.thursday_checkbox);
            CheckBox fridayBox = fragmentView.findViewById(R.id.friday_checkbox);

            String days = "";
            if (mondayBox.isChecked()) {
                days += 'm';
            }
            if (tuesdayBox.isChecked()) {
                days += 't';
            }
            if (wednesdayBox.isChecked()) {
                days += 'w';
            }
            if (thursdayBox.isChecked()) {
                days += 'r';
            }
            if (fridayBox.isChecked()) {
                days += 'f';
            }

            CourseDAO dao = new CourseDAO();

            dao.save(new Course.Builder()
                    .setName(courseName.getText().toString())
                    .setLocationName(selectedLocation)
                    .setStartTime(startTime)
                    .setEndTime(endTime)
                    .setDays(days)
                    .build());

            // Once the item is saved, navigate to schedule screen
            ((ScheduleFragment.ScheduleNavigationListener) getContext()).navigateToSchedule();
        });
    }

    private void initializeWithDefaultValues(Course course) {
        // Initialize the display
        EditText courseName = fragmentView.findViewById(R.id.course_name_edit_text);
        courseName.setText(course.getName());

        startTime = course.getStartTime();
        endTime = course.getEndTime();

        CheckBox mondayBox = fragmentView.findViewById(R.id.monday_checkbox);
        CheckBox tuesdayBox = fragmentView.findViewById(R.id.tuesday_checkbox);
        CheckBox wednesdayBox = fragmentView.findViewById(R.id.its_wednesday_my_dudes);
        CheckBox thursdayBox = fragmentView.findViewById(R.id.thursday_checkbox);
        CheckBox fridayBox = fragmentView.findViewById(R.id.friday_checkbox);

        for (char c : course.getDays().toCharArray()) {
            switch (c) {
                case 'm':
                    mondayBox.setChecked(true);
                    break;
                case 't':
                    tuesdayBox.setChecked(true);
                    break;
                case 'w':
                    wednesdayBox.setChecked(true);
                    break;
                case 'r':
                    thursdayBox.setChecked(true);
                    break;
                case 'f':
                    fridayBox.setChecked(true);
                    break;
            }
        }

        TextView startTimeDisplay = fragmentView.findViewById(R.id.start_time_display);
        startTimeDisplay.setText(startTime.toString());

        TextView endTimeDisplay = fragmentView.findViewById(R.id.end_time_display);
        endTimeDisplay.setText(endTime.toString());

        Button updateButton = fragmentView.findViewById(R.id.continue_btn);

        // on update button pressed
        updateButton.setOnClickListener((view) -> {
            String days = "";
            if (mondayBox.isChecked()) {
                days += 'm';
            }
            if (tuesdayBox.isChecked()) {
                days += 't';
            }
            if (wednesdayBox.isChecked()) {
                days += 'w';
            }
            if (thursdayBox.isChecked()) {
                days += 'r';
            }
            if (fridayBox.isChecked()) {
                days += 'f';
            }

            Spinner courseLocation = fragmentView.findViewById(R.id.course_location_spinner);
            String selectedLocation = courseLocation.getSelectedItem().toString();

            CourseDAO dao = new CourseDAO();
            dao.update(new Course.Builder()
                    .setName(courseName.getText().toString())
                    .setCourseId(course.getCourseId()) // preserve the course id, this is being passed in so the update method can update properly
                    .setStartTime(startTime)
                    .setEndTime(endTime)
                    .setDays(days)
                    .setLocationName(selectedLocation)
                    .build());
            ((ScheduleFragment.ScheduleNavigationListener) getContext()).navigateToSchedule();
        });
        updateButton.setText("Update Entry");
    }

    public interface CourseDetailsNavigationListener {
        // TODO this needs to take an argument so that the behavior of the original activity is preserved
        void navigateToCourseDetails();
    }


}

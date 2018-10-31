package com.example.kevin.mapdatabasesproject.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kevin.mapdatabasesproject.R;
import com.example.kevin.mapdatabasesproject.model.Course;

import java.util.List;

/**
 * Binds data from the application to the RecyclerView layout
 *
 * See: https://guides.codepath.com/android/using-the-recyclerview
 * for information about RecyclerViews
 */
public class ScheduleAdapter
        extends RecyclerView.Adapter<ScheduleAdapter.ScheduleItemViewHolder> {

    private List<Course> courseList;

    public ScheduleAdapter(List<Course> data) {
        this.courseList = data;
    }

    // Called to create the layout inside each element of the recyclerview
    @Override
    @NonNull
    public ScheduleItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.schedule_item, viewGroup, false);
        return new ScheduleItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ScheduleItemViewHolder viewHolder, int position) {
        Course course = courseList.get(position);
        viewHolder.courseName.setText(course.getCourseName());
        viewHolder.courseTime.setText(course.getCourseTime());
        viewHolder.courseNumber.setText(course.getCourseNumber());
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    /**
     * Stores references to layout elements in the RecyclerView
     */
    public final static class ScheduleItemViewHolder extends RecyclerView.ViewHolder {
        TextView courseName;
        TextView courseTime;
        TextView courseNumber;

        public ScheduleItemViewHolder(View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.class_name);
            courseTime = itemView.findViewById(R.id.class_time);
            courseNumber = itemView.findViewById(R.id.class_number);
        }
    }
}

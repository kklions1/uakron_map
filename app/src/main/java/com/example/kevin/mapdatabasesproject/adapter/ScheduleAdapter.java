package com.example.kevin.mapdatabasesproject.adapter;

import android.graphics.Color;
import android.graphics.Typeface;
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
    private OnItemClickListener onItemClickListener;

    public ScheduleAdapter(List<Course> data, OnItemClickListener listener) {
        this.courseList = data;
        this.onItemClickListener = listener;
    }

    // Called to create the layout inside each element of the recyclerview
    @Override
    @NonNull
    public ScheduleItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.schedule_item, viewGroup, false);
        return new ScheduleItemViewHolder(itemView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(ScheduleItemViewHolder viewHolder, int position) {
        Course course = courseList.get(position);
        viewHolder.courseName.setText(course.getName());
        viewHolder.courseStartTime.setText(course.getStartTime().toString());
        viewHolder.courseEndTime.setText(course.getEndTime().toString());
        viewHolder.courseLocation.setText(course.getLocationName());

        for (char c : course.getDays().toCharArray()) {
            switch (c) {
                case 'm':
                    viewHolder.mondayDisplay.setTypeface(null, Typeface.BOLD);
                    viewHolder.mondayDisplay.setTextColor(Color.BLACK);
                    break;
                case 't':
                    viewHolder.tuesdayDisplay.setTypeface(null, Typeface.BOLD);
                    viewHolder.tuesdayDisplay.setTextColor(Color.BLACK);
                    break;
                case 'w':
                    viewHolder.wednesdayMyDudes.setTypeface(null, Typeface.BOLD);
                    viewHolder.wednesdayMyDudes.setTextColor(Color.BLACK);
                    break;
                case 'r':
                    viewHolder.thursdayDisplay.setTypeface(null, Typeface.BOLD);
                    viewHolder.thursdayDisplay.setTextColor(Color.BLACK);
                    break;
                case 'f':
                    viewHolder.fridayDisplay.setTypeface(null, Typeface.BOLD);
                    viewHolder.fridayDisplay.setTextColor(Color.BLACK);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    /**
     * Stores references to layout elements in the RecyclerView
     */
    public final static class ScheduleItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView courseName;
        TextView courseStartTime;
        TextView courseLocation;
        TextView courseEndTime;
        TextView mondayDisplay;
        TextView tuesdayDisplay;
        TextView wednesdayMyDudes;
        TextView thursdayDisplay;
        TextView fridayDisplay;

        private ScheduleAdapter.OnItemClickListener listener;

        public ScheduleItemViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            courseName = itemView.findViewById(R.id.class_name);
            courseStartTime = itemView.findViewById(R.id.course_start_time);
            courseEndTime = itemView.findViewById(R.id.course_end_time);
            courseLocation = itemView.findViewById(R.id.course_location);
            mondayDisplay = itemView.findViewById(R.id.monday_display);
            tuesdayDisplay = itemView.findViewById(R.id.tuesday_display);
            wednesdayMyDudes = itemView.findViewById(R.id.its_wednesday_my_dudes_display);
            thursdayDisplay = itemView.findViewById(R.id.thursday_display);
            fridayDisplay = itemView.findViewById(R.id.friday_display);

            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.listener.onItemClick(v, getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
}

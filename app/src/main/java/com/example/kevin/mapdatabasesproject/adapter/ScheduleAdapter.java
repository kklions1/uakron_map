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
        viewHolder.courseStartTime.setText(course.getStartTimeHour() + ":" + course.getStartTimeMinute());
        viewHolder.courseEndTime.setText(course.getEndTimeHour() + ":" + course.getEndTimeMinute());
//        viewHolder.courseLocation.setText(Integer.toString(course.getLocationId()));
        viewHolder.courseLocation.setText(course.getLocationName());
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    // TODO using this method to add the click listener to the recyclerview would be better
    // because there is a looser coupling between the construction of the view and the initialization of
    // the listeners. for now, the current solution will have to work.
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    /**
     * Stores references to layout elements in the RecyclerView
     */
    public final static class ScheduleItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView courseName;
        TextView courseStartTime;
        TextView courseLocation;
        TextView courseEndTime;
        private ScheduleAdapter.OnItemClickListener listener;

        public ScheduleItemViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            courseName = itemView.findViewById(R.id.class_name);
            courseStartTime = itemView.findViewById(R.id.course_start_time);
            courseEndTime = itemView.findViewById(R.id.course_end_time);
            courseLocation = itemView.findViewById(R.id.course_location);
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

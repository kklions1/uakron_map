package com.example.kevin.mapdatabasesproject.fragment;

import android.app.Fragment;
import android.os.Bundle;

public class CourseDetailsFragment extends Fragment {
    // TODO this method needs an argument
    public static CourseDetailsFragment newInstance() {
        return new CourseDetailsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public interface CourseDetailsNavigationListener {
        // TODO this needs to take an argument so that the behavior of the original activity is preserved
        void navigateToCourseDetails();
    }


}

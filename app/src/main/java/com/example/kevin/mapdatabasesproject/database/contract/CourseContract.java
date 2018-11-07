package com.example.kevin.mapdatabasesproject.database.contract;

/**
 * Stores basic strings that name columns in Course table
 */
public interface CourseContract {
    String TABLE_NAME = "Courses";
    String COURSE_ID = "id";
    String COURSE_NAME = "name";
    String START_TIME_HOUR = "startTimeHour";
    String START_TIME_MINUTE = "startTimeMinute";
    String END_TIME_HOUR = "endTimeHour";
    String END_TIME_MINUTE = "endTimeMinute";
    String LOCATION_ID = "locationId";
}

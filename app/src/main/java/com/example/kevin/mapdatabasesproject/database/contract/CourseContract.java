package com.example.kevin.mapdatabasesproject.database.contract;

/**
 * Stores basic strings that name columns in Course table
 */
public interface CourseContract {
    String TABLE_NAME = "Courses";
    String COURSE_ID = "id";
    String COURSE_NAME = "name";
    String START_TIME = "starttime";
    String END_TIME = "endtime";
    String LOCATION_ID = "locationId";
}

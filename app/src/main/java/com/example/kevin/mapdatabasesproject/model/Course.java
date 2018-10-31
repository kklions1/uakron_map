package com.example.kevin.mapdatabasesproject.model;

/**
 * Class that represents a course
 */
public class Course {
    private String courseName;
    private String courseTime;
    private String courseNumber;

    public String getCourseName() {
        return this.courseName;
    }

    public String getCourseTime() {
        return this.courseTime;
    }

    public String getCourseNumber() {
        return this.courseNumber;
    }

    // TODO make this a builder pattern? If this class gets to the point where we can allow nulls,
    // this should be a builder pattern or use Optionals.

    public Course(String name, String time, String number) {
        this.courseName = name;
        this.courseTime = time;
        this.courseNumber = number;
    }
}

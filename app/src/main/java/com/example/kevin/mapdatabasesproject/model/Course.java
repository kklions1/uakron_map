package com.example.kevin.mapdatabasesproject.model;

import java.io.Serializable;

/**
 * Class that represents a course
 */
public class Course implements Serializable {

    private CourseTime startTime;
    private CourseTime endTime;
    private int courseId;
    private String name;
    private int locationId;
    private String locationName;
    private String days;

    private Course() {

    }

    public CourseTime getStartTime() { return this.startTime; }

    public CourseTime getEndTime() { return this.endTime; }

    public int getCourseId() { return this.courseId; }

    public String getName() { return this.name; }

    @Deprecated
    public int getLocationId() { return this.locationId; }

    public String getLocationName() { return this.locationName; }

    public String getDays() { return this.days; }

    public static class Builder {
        private CourseTime startTime;
        private CourseTime endTime;
        private int courseId;
        private String name;
        private int locationId;
        private String locationName;
        private String days;

        public Builder setStartTime(CourseTime time) {
            this.startTime = time;
            return this;
        }

        public Builder setEndTime(CourseTime time) {
            this.endTime = time;
            return this;
        }

        public Builder setCourseId(int courseId) {
            this.courseId = courseId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        @Deprecated
        public Builder setLocationId(int id) {
            this.locationId = id;
            return this;
        }

        public Builder setLocationName(String name) {
            this.locationName = name;
            return this;
        }

        public Builder setDays(String days) {
            this.days = days;
            return this;
        }

        public Course build() {
            Course newCourse = new Course();
            newCourse.courseId = this.courseId;
            newCourse.name = this.name;
            newCourse.startTime = this.startTime;
            newCourse.endTime = this.endTime;
            newCourse.locationId = this.locationId;
            newCourse.locationName = this.locationName;
            newCourse.days = this.days;

            return newCourse;
        }
    }
}

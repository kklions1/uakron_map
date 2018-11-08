package com.example.kevin.mapdatabasesproject.model;

/**
 * Class that represents a course
 */
public class Course {
    private int startTimeHour;
    private int startTimeMinute;
    private int endTimeHour;
    private int endTimeMinute;
    private int courseId;
    private String name;
    private int locationId;

    private Course() {

    }

    public int getStartTimeHour() { return this.startTimeHour; }

    public int getStartTimeMinute() { return this.startTimeMinute; }

    public int getEndTimeHour() { return this.endTimeHour; }

    public int getEndTimeMinute() { return this.endTimeMinute; }

    public int getCourseId() { return this.courseId; }

    public String getName() { return this.name; }

    public int getLocationId() { return this.locationId; }

    public static class Builder {
        private int startTimeHour;
        private int startTimeMinute;
        private int endTimeHour;
        private int endTimeMinute;
        private int courseId;
        private String name;
        private int locationId;


        public Builder setStartTimeHour(int hour) {
            this.startTimeHour = hour;
            return this;
        }

        public Builder setStartTimeMinute(int min) {
            this.startTimeMinute = min;
            return this;
        }

        public Builder setEndTimeHour(int hour) {
            this.endTimeHour = hour;
            return this;
        }

        public Builder setEndTimeMinute(int min) {
            this.endTimeMinute = min;
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

        public Builder setLocationId(int id) {
            this.locationId = id;
            return this;
        }

        public Course build() {
            Course newCourse = new Course();
            newCourse.courseId = this.courseId;
            newCourse.name = this.name;
            newCourse.startTimeHour = this.startTimeHour;
            newCourse.startTimeMinute = this.startTimeMinute;
            newCourse.endTimeHour = this.endTimeHour;
            newCourse.endTimeMinute = this.endTimeMinute;
            newCourse.locationId = this.locationId;

            return newCourse;
        }
    }
}

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
    private int locationId

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


        public void setStartTimeHour(int hour) {
            this.startTimeHour = hour;
        }

        public void setStartTimeMinute(int min) {
            this.startTimeMinute = min;
        }

        public void setEndTimeHour(int hour) {
            this.endTimeHour = hour;
        }

        public void setEndTimeMinute(int min) {
            this.endTimeMinute = min;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setLocationId(int id) {
            this.locationId = id;
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

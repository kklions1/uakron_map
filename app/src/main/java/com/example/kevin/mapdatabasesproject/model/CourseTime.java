package com.example.kevin.mapdatabasesproject.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Wrapper class for times, and handles formatting times
 */
public class CourseTime implements Serializable {
    private int hour;
    private int minute;

    public CourseTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public CourseTime() {
        this.hour = 0;
        this.minute = 0;
    }

    public int getHour() { return this.hour; }

    public int getMinute() { return this.minute; }

    public void setHour(int hour) { this.hour = hour; }

    public void setMinute(int minute) { this.minute = minute; }

    @Override
    public String toString() {
        SimpleDateFormat format;
        // Format if the hour is a single digit
        if (hour < 10) {
            format = new SimpleDateFormat("H:mm");
        } else {
            format = new SimpleDateFormat("HH:mm");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, this.hour);
        calendar.set(Calendar.MINUTE, this.minute);

        Date date = calendar.getTime();

        return format.format(date);

    }
}

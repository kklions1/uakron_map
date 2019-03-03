package com.example.kevin.mapdatabasesproject.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    public CourseTime(String time) {
        SimpleDateFormat format =  new SimpleDateFormat("hh:mm");
        Date date;
        Calendar cal = Calendar.getInstance();
        try {
            date = format.parse(time);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
            hour = 0;
            minute = 0;
            return;
        }

        if (date != null) {
            System.out.println("Date is not null");
            hour = cal.get(Calendar.HOUR_OF_DAY);
            minute = cal.get(Calendar.MINUTE);
        } else {
            System.out.println("Date is null");
            hour = 0;
            minute = 0;
        }
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

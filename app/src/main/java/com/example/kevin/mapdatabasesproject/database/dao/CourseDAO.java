package com.example.kevin.mapdatabasesproject.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.kevin.mapdatabasesproject.database.DatabaseHelper;
import com.example.kevin.mapdatabasesproject.database.contract.CourseContract;
import com.example.kevin.mapdatabasesproject.model.Course;
import com.example.kevin.mapdatabasesproject.model.CourseTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides an API for database operations on the Course Table
 */
public class CourseDAO implements DataAccessObject<Course> {
    private DatabaseHelper dbHelper;

    public CourseDAO() {
        dbHelper = DatabaseHelper.getInstance();
    }

    public List<Course> getAll() {
        List<Course> result = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // selection arguments are to help prevent SQL injection attacks
        Cursor cursor = db.rawQuery("SELECT * FROM " + CourseContract.TABLE_NAME + ";" , null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Course course = new Course.Builder()
                    .setName(cursor.getString(cursor.getColumnIndex(CourseContract.COURSE_NAME)))
                    .setCourseId(cursor.getInt(cursor.getColumnIndex(CourseContract.COURSE_ID)))
                    .setStartTime(new CourseTime(cursor.getInt(cursor.getColumnIndex(CourseContract.START_TIME_HOUR)),
                            cursor.getInt(cursor.getColumnIndex(CourseContract.START_TIME_MINUTE))))
                    .setEndTime(new CourseTime(cursor.getInt(cursor.getColumnIndex(CourseContract.END_TIME_HOUR)),
                            cursor.getInt(cursor.getColumnIndex(CourseContract.END_TIME_MINUTE))))
                    .setLocationName(cursor.getString(cursor.getColumnIndex(CourseContract.LOCATION_NAME)))
                    .setDays(cursor.getString(cursor.getColumnIndex(CourseContract.COURSE_DAYS)))
                    .build();

            result.add(course);

            // push resulting Course into result
            cursor.moveToNext();
        }

        cursor.close();
        return result;
    }

    @Override
    public void save(Course course) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("INSERT INTO " + CourseContract.TABLE_NAME + " (" +
                CourseContract.COURSE_NAME + "," +
                CourseContract.START_TIME_HOUR + "," +
                CourseContract.START_TIME_MINUTE + "," +
                CourseContract.END_TIME_HOUR + "," +
                CourseContract.END_TIME_MINUTE + "," +
                CourseContract.LOCATION_NAME + "," +
                CourseContract.COURSE_DAYS + ") VALUES (?, ?, ?, ?, ?, ?, ?);",
                new String[] {course.getName(), String.valueOf(course.getStartTime().getHour()), String.valueOf(course.getStartTime().getMinute()),
                    String.valueOf(course.getEndTime().getHour()), String.valueOf(course.getEndTime().getMinute()), course.getLocationName(),
                        course.getDays()});
    }

    @Override
    public void update(Course course) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.execSQL("UPDATE " + CourseContract.TABLE_NAME + " SET " +
                CourseContract.COURSE_NAME + " = ?, " +
                CourseContract.START_TIME_HOUR + " = ?, " +
                CourseContract.START_TIME_MINUTE + " = ?, " +
                CourseContract.END_TIME_HOUR + " = ?, " +
                CourseContract.END_TIME_MINUTE + " = ?, " +
                CourseContract.LOCATION_NAME + " = ?, " +
                CourseContract.COURSE_DAYS + " = ? " +
                " WHERE " + CourseContract.COURSE_ID + " = ?;", new String[] {course.getName(), String.valueOf(course.getStartTime().getHour()),
                    String.valueOf(course.getStartTime().getMinute()), String.valueOf(course.getEndTime().getHour()), String.valueOf(course.getEndTime().getMinute()),
                    course.getLocationName(), course.getDays(),
                    String.valueOf(course.getCourseId())});
    }

    @Override
    public void delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.execSQL("DELETE FROM " + CourseContract.TABLE_NAME + " WHERE " +
                CourseContract.COURSE_ID + " = ?", new String[] {String.valueOf(id)});
    }
}

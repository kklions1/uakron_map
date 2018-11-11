package com.example.kevin.mapdatabasesproject.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kevin.mapdatabasesproject.database.DatabaseHelper;
import com.example.kevin.mapdatabasesproject.database.contract.CourseContract;
import com.example.kevin.mapdatabasesproject.model.Course;

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
                    .setStartTimeHour(cursor.getInt(cursor.getColumnIndex(CourseContract.START_TIME_HOUR)))
                    .setStartTimeMinute(cursor.getInt(cursor.getColumnIndex(CourseContract.START_TIME_MINUTE)))
                    .setEndTimeHour(cursor.getInt(cursor.getColumnIndex(CourseContract.END_TIME_HOUR)))
                    .setEndTimeMinute(cursor.getInt(cursor.getColumnIndex(CourseContract.END_TIME_MINUTE)))
                    .setLocationId(12)
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
                CourseContract.COURSE_ID + ", " +
                CourseContract.START_TIME_HOUR + "," +
                CourseContract.START_TIME_MINUTE + "," +
                CourseContract.END_TIME_HOUR + "," +
                CourseContract.END_TIME_MINUTE + "," +
                CourseContract.LOCATION_ID + ") VALUES (" + "'" + // Values we expect to be strings need quotes for the SQL command
                course.getName() + "'" + ", " +
                course.getCourseId() + ", " +
                course.getStartTimeHour() + ", " +
                course.getStartTimeMinute() + ", " +
                course.getEndTimeHour() + ", " +
                course.getEndTimeMinute() + ", " +
                course.getLocationId() + ");");
    }

    // Returns the number of entries in the DB
    public int getCount() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + CourseContract.TABLE_NAME + ";", null);
        int result = cursor.getCount();
        cursor.close();
        return result;
    }

    public Course getCourseById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + CourseContract.TABLE_NAME + " WHERE " +
                CourseContract.COURSE_ID + " = ?;", new String[] {String.valueOf(id)});

        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            // TODO move this to a separate method
            Course course = new Course.Builder()
                    .setName(cursor.getString(cursor.getColumnIndex(CourseContract.COURSE_NAME)))
                    .setCourseId(cursor.getInt(cursor.getColumnIndex(CourseContract.COURSE_ID)))
                    .setStartTimeHour(cursor.getInt(cursor.getColumnIndex(CourseContract.START_TIME_HOUR)))
                    .setStartTimeMinute(cursor.getInt(cursor.getColumnIndex(CourseContract.START_TIME_MINUTE)))
                    .setEndTimeHour(cursor.getInt(cursor.getColumnIndex(CourseContract.END_TIME_HOUR)))
                    .setEndTimeMinute(cursor.getInt(cursor.getColumnIndex(CourseContract.END_TIME_MINUTE)))
                    .setLocationId(12)
                    .build();

            cursor.close();
            return course;
        }

        return null;
    }

    @Override
    public void update(Course course) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //
        db.execSQL("UPDATE " + CourseContract.TABLE_NAME + " SET " +
                CourseContract.COURSE_NAME + " = '" + course.getName() + "', " +
                CourseContract.START_TIME_HOUR + " = " + course.getStartTimeHour() + ", " +
                CourseContract.START_TIME_MINUTE + " = " + course.getStartTimeMinute() + ", " +
                CourseContract.END_TIME_HOUR + " = " + course.getEndTimeHour() + ", " +
                CourseContract.END_TIME_MINUTE + " = " + course.getEndTimeMinute() + ", " +
                CourseContract.LOCATION_ID + " = " + course.getLocationId() +
                " WHERE " + CourseContract.COURSE_ID + " = ?;", new String[] {Integer.toString(course.getCourseId())});
    }
}

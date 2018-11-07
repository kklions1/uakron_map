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
        Cursor cursor = db.rawQuery("SELECT * FROM Courses", null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String courseName = cursor.getString(cursor.getColumnIndex(CourseContract.TABLE_NAME));

            // push resulting Course into result
            cursor.moveToNext();
        }


        cursor.close();
        return result;
    }

}

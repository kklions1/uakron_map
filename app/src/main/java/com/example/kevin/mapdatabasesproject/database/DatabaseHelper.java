package com.example.kevin.mapdatabasesproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kevin.mapdatabasesproject.database.contract.CourseContract;


/**
 * Implemented with singleton pattern because there should really only ever be one database
 * manager during runtime. Singleton is an anti-pattern, but it is useful here
 *
 * Note: Access to an instance of the database helper allows access to the database itself.
 * Using the DAO pattern is intended to provide an API into the database, but there is a security
 * flaw in the current implementation where you can just getInstance() and have direct access anyway.
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "akron_map.db";
    private static final int DATABASE_VERSION = 1;

    private static DatabaseHelper instance;

    public static void setInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }
    }

    public static DatabaseHelper getInstance() {
        return instance;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        // TODO verify against data model
        database.execSQL("CREATE TABLE IF NOT EXISTS Courses ( " +
                CourseContract.COURSE_ID + " INTEGER PRIMARY KEY, " +
                CourseContract.COURSE_NAME + " CHAR(30) NOT NULL," +
                CourseContract.START_TIME_HOUR + " INTEGER," +
                CourseContract.START_TIME_MINUTE + " INTEGER," +
                CourseContract.END_TIME_HOUR + " INTEGER," +
                CourseContract.END_TIME_MINUTE + " INTEGER," +
                CourseContract.LOCATION_ID + " INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int currentVersion, int newVersion) {
        // Not sure what this is supposed to do TBH
    }


}

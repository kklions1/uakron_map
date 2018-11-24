package com.example.kevin.mapdatabasesproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kevin.mapdatabasesproject.database.contract.CourseContract;
import com.example.kevin.mapdatabasesproject.database.contract.LocationContract;


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

        // Create Courses table
        database.execSQL("CREATE TABLE IF NOT EXISTS " + CourseContract.TABLE_NAME + " ( " +
                CourseContract.COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CourseContract.COURSE_NAME + " CHAR(30) NOT NULL," +
                CourseContract.START_TIME_HOUR + " INTEGER," +
                CourseContract.START_TIME_MINUTE + " INTEGER," +
                CourseContract.END_TIME_HOUR + " INTEGER," +
                CourseContract.END_TIME_MINUTE + " INTEGER," +
                CourseContract.LOCATION_ID + " INTEGER);");

        // Create Locations table
        database.execSQL("CREATE TABLE IF NOT EXISTS " + LocationContract.TABLE_NAME + " ( " +
                LocationContract.LOC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LocationContract.LAT + " REAL NOT NULL, " +
                LocationContract.LNG + " REAL NOT NULL, " +
                LocationContract.TITLE + " TEXT);");

        initializeLocations(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int currentVersion, int newVersion) {
        // Not sure what this is supposed to do TBH
    }

    // Initialize pre-set locations in the table
    private void initializeLocations(SQLiteDatabase database) {
        database.execSQL("INSERT OR IGNORE INTO " + LocationContract.TABLE_NAME + " (" +
                LocationContract.LAT + ", " +
                LocationContract.LNG + ", " +
                LocationContract.TITLE + ") VALUES (?, ?, ?);", new String[] {"41.07564347775708",
                            "-81.51244461536409", "Student Union"});

        database.execSQL("INSERT OR IGNORE INTO " +)

    }


}

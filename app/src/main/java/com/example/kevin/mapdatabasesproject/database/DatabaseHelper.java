package com.example.kevin.mapdatabasesproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kevin.mapdatabasesproject.database.contract.CourseContract;
import com.example.kevin.mapdatabasesproject.database.contract.LocationContract;
import com.example.kevin.mapdatabasesproject.database.contract.LocationNames;


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
                CourseContract.COURSE_NAME + " TEXT NOT NULL," +
                CourseContract.START_TIME_HOUR + " INTEGER," +
                CourseContract.START_TIME_MINUTE + " INTEGER," +
                CourseContract.END_TIME_HOUR + " INTEGER," +
                CourseContract.END_TIME_MINUTE + " INTEGER," +
                CourseContract.COURSE_DAYS + " TEXT," +
                CourseContract.LOCATION_NAME + " TEXT);");

        // Reset the locations table
        database.execSQL("DROP TABLE IF EXISTS " + LocationContract.TABLE_NAME + ";");

        // Create Locations table
        database.execSQL("CREATE TABLE IF NOT EXISTS " + LocationContract.TABLE_NAME + " ( " +
                LocationContract.LOC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LocationContract.LAT + " TEXT NOT NULL, " +
                LocationContract.LNG + " TEXT NOT NULL, " +
                LocationContract.TITLE + " TEXT);");

        initializeLocations(database);

        // Create Custom Locations Table, while these tables are similar, they are used for different things
        database.execSQL("CREATE TABLE IF NOT EXISTS " + LocationContract.CUSTOM_LOC_TABLE_NAME + " ( " +
                LocationContract.LOC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LocationContract.LAT + " TEXT NOT NULL, " +
                LocationContract.LNG + " TEXT NOT NULL, " +
                LocationContract.TITLE + " TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int currentVersion, int newVersion) {
        // Not sure what this is supposed to do TBH
    }

    // Initialize pre-set locations in the table
    // Ignore Any insertions for values already in the table
    private void initializeLocations(SQLiteDatabase database) {
        database.execSQL("INSERT INTO " + LocationContract.TABLE_NAME + " (" +
                LocationContract.TITLE + ", " +
                LocationContract.LAT + ", " +
                LocationContract.LNG + ") VALUES (?, ?, ?);", new String[] {LocationNames.STUDENT_UNION,
                "41.07564347775708", "-81.51244461536409"});

        database.execSQL("INSERT INTO " + LocationContract.TABLE_NAME + " (" +
                LocationContract.TITLE + ", " +
                LocationContract.LAT + ", " +
                LocationContract.LNG + ") VALUES (?, ?, ?);", new String[] {LocationNames.LEIGH,
                "41.07620381211137", "-81.5107672289014"});

        database.execSQL("INSERT INTO " + LocationContract.TABLE_NAME + " (" +
                LocationContract.TITLE + ", " +
                LocationContract.LAT + ", " +
                LocationContract.LNG + ") VALUES (?, ?, ?);", new String[] {LocationNames.CROUSE,
                "41.07629378867585", "-81.51218745857477"});

        database.execSQL("INSERT INTO " + LocationContract.TABLE_NAME + " (" +
                LocationContract.TITLE + ", " +
                LocationContract.LAT + ", " +
                LocationContract.LNG + ") VALUES (?, ?, ?);", new String[] {LocationNames.REC_CENTER,
                "41.07466509269993", "-81.50861509144306"});

        database.execSQL("INSERT INTO " + LocationContract.TABLE_NAME + " (" +
                LocationContract.TITLE + ", " +
                LocationContract.LAT + ", " +
                LocationContract.LNG + ") VALUES (?, ?, ?);", new String[] {LocationNames.ARTS_AND_SCIENCES,
                "41.07778697727521", "-81.51058919727802"});

        database.execSQL("INSERT INTO " + LocationContract.TABLE_NAME + " (" +
                LocationContract.TITLE + ", " +
                LocationContract.LAT + ", " +
                LocationContract.LNG + ") VALUES (?, ?, ?);", new String[] {LocationNames.KOLBE,
                "41.076206086801335", "-81.51016104966403"});

        database.execSQL("INSERT INTO " + LocationContract.TABLE_NAME + " (" +
                LocationContract.TITLE + ", " +
                LocationContract.LAT + ", " +
                LocationContract.LNG + ") VALUES (?, ?, ?);", new String[] {LocationNames.POLSKY,
                "41.07872993317009", "-81.51958733797072"});

        database.execSQL("INSERT INTO " + LocationContract.TABLE_NAME + " (" +
                LocationContract.TITLE + ", " +
                LocationContract.LAT + ", " +
                LocationContract.LNG + ") VALUES (?, ?, ?);", new String[] {LocationNames.BUSINESS_ADMIN,
                "41.077523119062526", "-81.51765648275614"});
    }
}

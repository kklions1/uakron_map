package com.example.kevin.mapdatabasesproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Implemented with singleton pattern because there should really only ever be one database
 * manager during runtime. Singleton is an anti-pattern, but it is useful here
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
        // TODO verify against datamodel
        database.execSQL("CREATE TABLE IF NOT EXISTS Courses ( " +
                "id INTEGER PRIMARY KEY, " +
                "name CHAR(30) NOT NULL," +
                "starttime REAL NOT NULL," +
                "endtime REAL NOT NULL," +
                "locationId INTEGER NOT NULL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int currentVersion, int newVersion) {
        // Not sure what this is supposed to do TBH
    }


}

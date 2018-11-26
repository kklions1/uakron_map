package com.example.kevin.mapdatabasesproject.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.kevin.mapdatabasesproject.database.DatabaseHelper;
import com.example.kevin.mapdatabasesproject.database.contract.CourseContract;
import com.example.kevin.mapdatabasesproject.database.contract.LocationContract;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class LocationDAO implements DataAccessObject<MarkerOptions> {
    private DatabaseHelper dbHelper;

    public LocationDAO() {
        this.dbHelper = DatabaseHelper.getInstance();
    }

    public List<MarkerOptions> getAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<MarkerOptions> result = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + LocationContract.TABLE_NAME + ";", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            double latitude = Double.valueOf(cursor.getString(cursor.getColumnIndex(LocationContract.LAT)));
            double longitude = Double.valueOf(cursor.getString(cursor.getColumnIndex(LocationContract.LNG)));
            String title = cursor.getString(cursor.getColumnIndex(LocationContract.TITLE));

            MarkerOptions marker = new MarkerOptions()
                    .position(new LatLng(latitude, longitude))
                    .title(title);

            result.add(marker);
            cursor.moveToNext();
        }

        cursor.close();

        return result;
    }

    @Override
    public void save(MarkerOptions location) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.execSQL("INSERT INTO " + LocationContract.TABLE_NAME + " (" +
                LocationContract.LAT + ", " +
                LocationContract.LNG + ", " +
                LocationContract.TITLE + ") VALUES (?, ?, ?);", new String[] {String.valueOf(location.getPosition().latitude),
                                String.valueOf(location.getPosition().longitude), location.getTitle()});
    }

    @Override
    public void update(MarkerOptions location) {
        // Cannot update this table because it is preset constant values
    }

    @Override
    public void delete(int id) {
        // Can't delete from this particular table yet, since its only preset values are allowed to be in it
    }

    // Fetches all locations that are associated with a course using a join with the courses table
    public List<MarkerOptions> fetchCourseLocations() {
        List<MarkerOptions> result = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // separate string for debugging purposes
        String query = "SELECT " + LocationContract.LAT + ", " + LocationContract.LNG + ", " +
                LocationContract.TITLE  + " FROM " + LocationContract.TABLE_NAME + " INNER JOIN " + CourseContract.TABLE_NAME +
                " ON " + LocationContract.TABLE_NAME + "." + LocationContract.TITLE + " = " +
                CourseContract.TABLE_NAME + "." + CourseContract.LOCATION_NAME + ";";

        Cursor cursor = db.rawQuery(query, null);
        cursor.getCount();
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(new LatLng(Double.valueOf(cursor.getString(cursor.getColumnIndex(LocationContract.LAT))),
                            Double.valueOf(cursor.getString(cursor.getColumnIndex(LocationContract.LNG)))))
                    .title(cursor.getString(cursor.getColumnIndex(LocationContract.TITLE)));

            result.add(markerOptions);

            cursor.moveToNext();
        }
        cursor.close();
        return result;
    }
}

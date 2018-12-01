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

public class CustomLocationDAO implements DataAccessObject<MarkerOptions> {
    private DatabaseHelper dbHelper;

    public CustomLocationDAO() {
        dbHelper = DatabaseHelper.getInstance();
    }

    @Override
    public void save(MarkerOptions marker) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("INSERT INTO " + LocationContract.TABLE_NAME + " (" +
                LocationContract.TITLE + ", " +
                LocationContract.LAT + ", " +
                LocationContract.LNG + ") VALUES (?, ?, ?);", new String[] {marker.getTitle(),
                Double.toString(marker.getPosition().latitude), Double.toString(marker.getPosition().longitude)});
    }

    @Override
    public List<MarkerOptions> getAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<MarkerOptions> result = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + LocationContract.CUSTOM_LOC_TABLE_NAME + ";", null);
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
    public void update(MarkerOptions marker) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

//        db.execSQL("UPDATE " + CourseContract.TABLE_NAME + " SET " +
//                CourseContract.COURSE_NAME + " = ?, " +
//                CourseContract.START_TIME_HOUR + " = ?, " +
//                CourseContract.START_TIME_MINUTE + " = ?, " +
//                CourseContract.END_TIME_HOUR + " = ?, " +
//                CourseContract.END_TIME_MINUTE + " = ?, " +
//                CourseContract.LOCATION_NAME + " = ?, " +
//                CourseContract.COURSE_DAYS + " = ? " +
//                " WHERE " + CourseContract.COURSE_ID + " = ?;", new String[] {course.getName(), String.valueOf(course.getStartTimeHour()),
//                String.valueOf(course.getStartTimeMinute()), String.valueOf(course.getEndTimeHour()), String.valueOf(course.getEndTimeMinute()),
//                course.getLocationName(), course.getDays(),
//                String.valueOf(course.getCourseId())});

        db.execSQL("UPDATE " + LocationContract.CUSTOM_LOC_TABLE_NAME + " SET " +
                LocationContract.TITLE + " = ?, " +
                LocationContract.LAT + " = ?, " +
                LocationContract.LNG + " = ?, " +

    }

    @Override
    public void delete(int id) {

    }
}

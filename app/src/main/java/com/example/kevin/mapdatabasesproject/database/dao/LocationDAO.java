package com.example.kevin.mapdatabasesproject.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.kevin.mapdatabasesproject.database.DatabaseHelper;
import com.example.kevin.mapdatabasesproject.database.contract.LocationContract;
import com.example.kevin.mapdatabasesproject.model.Location;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class LocationDAO implements DataAccessObject<Location> {
    private DatabaseHelper dbHelper;

    public LocationDAO() {
        this.dbHelper = DatabaseHelper.getInstance();
    }

    public List<Location> getAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Location> result = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + LocationContract.TABLE_NAME + ";", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Location.Builder locationBuilder = new Location.Builder();
            long latitude = cursor.getLong(cursor.getColumnIndex(LocationContract.LAT));
            long longitude = cursor.getLong(cursor.getColumnIndex(LocationContract.LNG));
            int id = cursor.getInt(cursor.getColumnIndex(LocationContract.LOC_ID));
            String title = cursor.getString(cursor.getColumnIndex(LocationContract.TITLE));

            MarkerOptions marker = new MarkerOptions()
                    .position(new LatLng(latitude, longitude))
                    .title(title);

            Location location = locationBuilder.setId(id)
                    .setMarker(marker)
                    .build();

            result.add(location);
        }

        cursor.close();

        return result;
    }

    @Override
    public void save(Location location) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.execSQL("INSERT INTO " + LocationContract.TABLE_NAME + " (" +
                LocationContract.LAT + ", " +
                LocationContract.LNG + ", " +
                LocationContract.TITLE + ") VALUES (?, ?, ?)", new String[] {String.valueOf(location.getMarker().getPosition().latitude),
                                String.valueOf(location.getMarker().getPosition().longitude), location.getTitle()};
    }

    @Override
    public void update(Location location) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

//        db.execSQL("UPDATE ");
    }

    @Override
    public void delete(int id) {

    }
}

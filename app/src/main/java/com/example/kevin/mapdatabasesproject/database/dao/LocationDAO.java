package com.example.kevin.mapdatabasesproject.database.dao;

import com.example.kevin.mapdatabasesproject.database.DatabaseHelper;
import com.example.kevin.mapdatabasesproject.model.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationDAO implements DataAccessObject<Location> {
    private DatabaseHelper dbHelper;

    public LocationDAO() {
        this.dbHelper = DatabaseHelper.getInstance();
    }

    public List<Location> getAll() {
        return new ArrayList<>();
    }

    @Override
    public void save(Location location) {

    }

    @Override
    public void update(Location location) {

    }

    @Override
    public void delete(int id) {

    }
}

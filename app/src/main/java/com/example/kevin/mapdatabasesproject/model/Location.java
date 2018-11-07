package com.example.kevin.mapdatabasesproject.model;

import com.google.android.gms.maps.model.Marker;

public class Location {
    private Marker marker;
    private int id;

    public Marker getMarker() { return this.marker; }

    public int getId() { return this.id; }
}

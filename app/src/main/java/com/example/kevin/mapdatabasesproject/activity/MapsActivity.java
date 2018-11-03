package com.example.kevin.mapdatabasesproject.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.kevin.mapdatabasesproject.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap googleMap;
    private Map<Marker, Integer> markerIdMap;

    // Activity-level onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        FloatingActionButton scheduleFab = findViewById(R.id.view_schedule_fab);
        scheduleFab.setOnClickListener((view) -> navigateToScheduleScreen());

        markerIdMap = new HashMap<>();
    }

    // GoogleMap onCreate
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        this.googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        this.googleMap.setOnMapLongClickListener(this);
        this.googleMap.setOnMarkerClickListener(this);
    }

    @Override
    public void onMapLongClick(LatLng point) {
        // We want all the markers on the GoogleMap to have an ID associated with them.  Since googlemap.addmarker returns a reference
        // to that marker, we can use it to place it inside a map with an ID associated with it
        markerIdMap.put(googleMap.addMarker(new MarkerOptions()
            .position(point)
            .title("Test Marker")), markerIdMap.size() + 1);

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    private void navigateToScheduleScreen() {
        Intent intent = new Intent(MapsActivity.this, ScheduleActivity.class);
        startActivity(intent);
    }

    public Map<Marker, Integer> getMarkerIdMap() { return markerIdMap; }
}
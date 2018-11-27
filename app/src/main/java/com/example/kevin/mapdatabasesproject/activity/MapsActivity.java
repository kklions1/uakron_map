package com.example.kevin.mapdatabasesproject.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.kevin.mapdatabasesproject.R;
import com.example.kevin.mapdatabasesproject.database.dao.LocationDAO;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap googleMap;
    // Upper left bound near polsky, lower right bound near the football field.

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

    }

    // GoogleMap onCreate
    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        final LatLngBounds akronMapBounds = new LatLngBounds(new LatLng(41.07047058512511, -81.50574445724489),
                new LatLng(41.081130353246074, -81.52067899703981));
        // Add a marker in Sydney and move the camera
        googleMap.setOnMapLongClickListener(this);
        googleMap.setOnMarkerClickListener(this);

        loadUsedMarkers();

        googleMap.setLatLngBoundsForCameraTarget(akronMapBounds);
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(akronMapBounds));
//        googleMap.setMaxZoomPreference(10f);
//        googleMap.setMinZoomPreference(15f);
//        CameraPosition defaultCameraPosition = new CameraPosition.Builder()
//                .target(studentUnion)
//                .zoom(10f)
//                .bearing(0)
//                .tilt(0)
//                .build();
//
//        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(defaultCameraPosition));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(studentUnion));
//        googleMap.setMinZoomPreference(15f);

    }

    @Override
    public void onMapLongClick(LatLng point) {
        // TODO am i even going to add custom location support at this point without a partner?
        googleMap.addMarker(new MarkerOptions()
            .position(point)
            .title("Test Marker")); // used to figure out what the lat and lng of the buildings i want are. this will be removed later
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.d("Location", "Lat: " + marker.getPosition().latitude + "\tLng: " + marker.getPosition().longitude);
        return false;
    }

    private void navigateToScheduleScreen() {
        Intent intent = new Intent(MapsActivity.this, ScheduleActivity.class);
        startActivity(intent);
    }

    private void loadUsedMarkers() {
        LocationDAO dao = new LocationDAO();

        List<MarkerOptions> courseMarkers = dao.fetchCourseLocations();

        for (MarkerOptions marker : courseMarkers) {
            googleMap.addMarker(marker);
        }

    }
}
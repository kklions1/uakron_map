package com.example.kevin.mapdatabasesproject.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
        GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener, ActivityCompat.OnRequestPermissionsResultCallback,
        LocationListener {

    private GoogleMap googleMap;
    private LocationManager locationManager;
    private String provider;
    public static final int MY_PERMISSION_REQUEST_LOCATION = 99;

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

        checkLocationPermission();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);

        locationManager.getLastKnownLocation(provider);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(provider, 400, 1, this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager.removeUpdates(this);
        }
    }

    // GoogleMap onCreate
    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        final LatLngBounds akronMapBounds = new LatLngBounds(new LatLng(41.07411485528487, -81.51851713657379),
                new LatLng(41.08216727323222, -81.5040811523795));

        googleMap.setOnMapLongClickListener(this);
        googleMap.setOnMarkerClickListener(this);

        loadUsedMarkers();

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(akronMapBounds.getCenter(), 20));
        googleMap.setLatLngBoundsForCameraTarget(akronMapBounds);

        googleMap.setMinZoomPreference(17f);

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

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(this)
                        .setTitle("Use Location")
                        .setMessage("Use Location")
                        .setPositiveButton("OK", (dialogInterface, i) -> {
                            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    MY_PERMISSION_REQUEST_LOCATION);
                        })
                        .create()
                        .show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSION_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        locationManager.requestLocationUpdates(provider, 400, 1, this);
                    }
                } else {
                    // Okay, let's be real here, this is a demo, and I'm never going to NOT enable location services
                    // so lets just leave this part blank, and move on with our lives
                }
                break;
        }
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
}
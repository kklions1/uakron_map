package com.example.kevin.mapdatabasesproject.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.kevin.mapdatabasesproject.R;
import com.example.kevin.mapdatabasesproject.database.dao.LocationDAO;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener, ActivityCompat.OnRequestPermissionsResultCallback {

    private GoogleMap googleMap;
    private boolean permissionGranted;
    public static final int PERMISSION_RESPONSE_CODE = 1;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Location lastKnownLocation;
    private LatLngBounds akronMapBounds;
    private LocationCallback locationCallback;

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

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult result) {
                for (Location l : result.getLocations()) {
                    lastKnownLocation = l;
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        startLocationUpdates();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    // GoogleMap onCreate
    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        akronMapBounds = new LatLngBounds(new LatLng(41.07411485528487, -81.51851713657379),
                new LatLng(41.08216727323222, -81.5040811523795));

        googleMap.setOnMapLongClickListener(this);
        googleMap.setOnMarkerClickListener(this);

        googleMap.setLatLngBoundsForCameraTarget(akronMapBounds);

        getLocationPermissions();
        updateLocationUI();
        getDeviceLocation();
        loadUsedMarkers();

        googleMap.setMinZoomPreference(15f);
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

    private void getLocationPermissions() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            permissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, PERMISSION_RESPONSE_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        permissionGranted = false;
        switch (requestCode) {
            case PERMISSION_RESPONSE_CODE: {
                // seems like a hack, but google's documentation specifically calls for this
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }

    private void updateLocationUI() {
        if (googleMap == null) {
            return;
        }
        try {
            if (permissionGranted) {
                // Enable locations
                googleMap.setMyLocationEnabled(true);
                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                // disable locations
                googleMap.setMyLocationEnabled(false);
                googleMap.getUiSettings().setMyLocationButtonEnabled(false);
                getLocationPermissions();
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void getDeviceLocation() {
        try {
            Task locationResult = fusedLocationProviderClient.getLastLocation();
            locationResult.addOnCompleteListener(this,(task) -> {
                if (task.isSuccessful()) {
                    lastKnownLocation = (Location) task.getResult();
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lastKnownLocation.getLatitude(),
                            lastKnownLocation.getLongitude()), 15f));
                } else {
                    Log.d("Yeet", "Current Location is null");
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(akronMapBounds.getCenter(), 15f));
                    googleMap.getUiSettings().setMyLocationButtonEnabled(false);
                }
            });
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void startLocationUpdates() {
        fusedLocationProviderClient.requestLocationUpdates(LocationRequest.create(), locationCallback, null);
    }

    private void stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    // https://developer.android.com/training/location/receive-location-updates#java
    // https://developers.google.com/maps/documentation/android-sdk/current-place-tutorial
}
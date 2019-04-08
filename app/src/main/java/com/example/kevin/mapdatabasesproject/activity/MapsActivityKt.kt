package com.example.kevin.mapdatabasesproject.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.FragmentActivity
import com.example.kevin.mapdatabasesproject.R
import com.example.kevin.mapdatabasesproject.database.dao.LocationDAO
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

class MapsActivityKt: FragmentActivity(), OnMapReadyCallback {
    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = (supportFragmentManager.findFragmentById(R.id.map)
                as SupportMapFragment)

        mapFragment.getMapAsync(this)

        val scheduleFab = findViewById<FloatingActionButton>(R.id.view_schedule_fab)
        scheduleFab.setOnClickListener {
            navigateToScheduleScreen()
        }

        val logoutFab = findViewById<FloatingActionButton>(R.id.logout_fab)
        logoutFab.setOnClickListener {

            val preferences = getSharedPreferences(getString(R.string.shared_preferences_key), Context.MODE_PRIVATE)
            val editor = preferences.edit()

            editor.clear()
            editor.apply()

            navigateToLoginScreen()
        }
    }

    override fun onMapReady(map: GoogleMap) {
        this.googleMap = map

        val akronMapBounds = LatLngBounds(LatLng(41.07411485528487, -81.51851713657379),
                LatLng(41.08216727323222, -81.5040811523795))

        googleMap.setLatLngBoundsForCameraTarget(akronMapBounds)
        googleMap.setMinZoomPreference(15f)
        loadUsedMarkers()
    }

    private fun loadUsedMarkers() {
        val dao = LocationDAO()

        val courseMarkers = dao.fetchCourseLocations()

        for (marker in courseMarkers) {
            googleMap.addMarker(marker)
        }

    }

    private fun navigateToScheduleScreen() {
        intent = Intent(this, ScheduleActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToLoginScreen() {
        intent = Intent(this, MeanderActivity::class.java)
        startActivity(intent)
    }
}
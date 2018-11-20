package com.example.kevin.mapdatabasesproject.model;

import android.os.Parcelable;

import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;

/**
 * Wrapper class for location information
 */
public class Location implements Serializable {
    private MarkerOptions marker;
    private int id;
    private String title;

    public MarkerOptions getMarker() { return this.marker; }

    public int getId() { return this.id; }

    public String getTitle() { return this.title; }

    public static final class Builder {
        private MarkerOptions marker;
        private int id;
        private String title;

        public Builder setMarker(MarkerOptions marker) {
            this.marker = marker;
            return this;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Location build() {
            Location location = new Location();
            location.marker = this.marker;
            location.id = this.id;
            location.title = this.title;
            return location;
        }
    }
}

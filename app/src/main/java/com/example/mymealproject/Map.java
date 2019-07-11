package com.example.mymealproject;

import android.location.Geocoder;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineCallback;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.plugins.locationlayer.LocationLayerPlugin;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapzen.android.lost.api.LocationListener;

import java.util.List;

public class Map extends AppCompatActivity{

        private MapView mapView;
        private MapboxMap map;
        private PermissionsManager permissionsManager;
        private LocationEngine locationEngine;
        private LocationLayerPlugin locationLayerPlugin;
        private Location originLocation;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Mapbox.getInstance(this, getString(R.string.access_token));
            setContentView(R.layout.activity_map);
            mapView = findViewById(R.id.mapView);
            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(@NonNull final MapboxMap mapboxMap) {

                    mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                        @Override
                        public void onStyleLoaded(@NonNull Style style) {

// Add the marker image to map
//                            style.addImage("marker-icon-id",
//                                    BitmapFactory.decodeResource(
//                                            Map.this.getResources(), R.drawable.mapbox_marker_icon_default));
//
//                            GeoJsonSource geoJsonSource = new GeoJsonSource("source-id", Feature.fromGeometry(
//                                    Point.fromLngLat(-87.679, 41.885)));
//                            style.addSource(geoJsonSource);
//
//                            SymbolLayer symbolLayer = new SymbolLayer("layer-id", "source-id");
//                            symbolLayer.withProperties(
//                                    PropertyFactory.iconImage("marker-icon-id")
//                            );
//                            style.addLayer(symbolLayer);

                        }
                    });
                }
            });
        }

        // Add the mapView's own lifecycle methods to the activity's lifecycle methods
        @Override
        public void onStart() {
            super.onStart();
            mapView.onStart();
        }

        @Override
        public void onResume() {
            super.onResume();
            mapView.onResume();
        }

        @Override
        public void onPause() {
            super.onPause();
            mapView.onPause();
        }

        @Override
        public void onStop() {
            super.onStop();
            mapView.onStop();
        }

        @Override
        public void onLowMemory() {
            super.onLowMemory();
            mapView.onLowMemory();
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            mapView.onDestroy();
        }

        @Override
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            mapView.onSaveInstanceState(outState);
        }



}


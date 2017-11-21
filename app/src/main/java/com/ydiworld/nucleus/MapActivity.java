package com.ydiworld.nucleus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by sammy on 11/21/17.
 */

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap googleMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap = googleMap;

        Double lat = 6.676570;
        Double lan = 3.172490;
        LatLng latLng = new LatLng(lat, lan);
        googleMap.addMarker(new MarkerOptions().position(latLng).title("Faith Academy, Canaanland Ota"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));

        MapStyleOptions mapStyleOptions = MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json);
        googleMap.setMapStyle(mapStyleOptions);
    }
}

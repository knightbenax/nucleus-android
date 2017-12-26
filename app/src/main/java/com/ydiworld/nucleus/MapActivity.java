package com.ydiworld.nucleus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.uber.sdk.android.rides.RideParameters;
import com.uber.sdk.android.rides.RideRequestActivityBehavior;
import com.uber.sdk.android.rides.RideRequestButton;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

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

        setThingsUp();
    }

    public void showMaps(View view){
        Uri gmmIntentUri = Uri.parse("google.navigation:q=6.676570,3.172490");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        if (mapIntent.resolveActivity(getPackageManager()) != null) {

            startActivity(mapIntent);
        }

    }

    public void callUber(View view){
        RideRequestButton rideRequestButton = new RideRequestButton(this);
        //layout.addView(rideRequestButton);
        Activity activity = this; // If you're in a fragment you must get the containing Activity!
        int requestCode = 1234;
        rideRequestButton.setRequestBehavior(new RideRequestActivityBehavior(activity, requestCode));
        // Optional, default behavior is to use current location for pickup
        Double lat = 6.676570;
        Double lan = 3.172490;
        RideParameters rideParams = new RideParameters.Builder()
                //.setProductId("a1111c8c-c720-46c3-8534-2fcdd730040d")
                //.setPickupLocation(37.775304, -122.417522, "Uber HQ", "1455 Market Street, San Francisco")
                .setDropoffLocation(6.676570, 3.172490, "Camp Joseph 2017", "Faith Academy, Canaanland Ota")
                .build();
        rideRequestButton.setRideParameters(rideParams);

        rideRequestButton.performClick();

    }

    private void setThingsUp(){
        setWinFlags();

        ImageView user = findViewById(R.id.person);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void clearWinFlags(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    public void setWinFlags(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap = googleMap;

        Double lat = 6.676570;
        Double lan = 3.172490;
        LatLng latLng = new LatLng(lat, lan);
        googleMap.addMarker(new MarkerOptions().position(latLng).title("Faith Academy, Canaanland Ota").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));

        MapStyleOptions mapStyleOptions = MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json);
        googleMap.setMapStyle(mapStyleOptions);
    }
}

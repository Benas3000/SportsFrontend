package com.example.jonas.map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class CreateActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private CreateActivity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        thisActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                Intent intent = new Intent(thisActivity, CreateFormActivity.class);
                Double lat = (double)Math.round(point.latitude * 1000000d) / 1000000d;
                Double lon = (double)Math.round(point.longitude * 1000000d) / 1000000d;
                String latLng = lat +  "," + lon;
                intent.putExtra("latLng", latLng);
                startActivity(intent);
            }
        });
        LatLng kaunas = new LatLng(54.899077, 23.935309);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kaunas));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(kaunas)
                .zoom(13).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}

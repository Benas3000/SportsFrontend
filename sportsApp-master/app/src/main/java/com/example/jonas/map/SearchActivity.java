package com.example.jonas.map;

import android.content.Intent;
import android.icu.util.Calendar;
import android.net.sip.SipAudioCall;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SearchActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<Point> places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        StringHandler stringHandler = new StringHandler();
        places = stringHandler.convertToPoints((ArrayList<String>) getIntent().getSerializableExtra("Points"));


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        try {
            populatePlaces();
        } catch (IOException | InterruptedException | JSONException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        addMarkers();

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()
        {
            @Override
            public void onInfoWindowClick(Marker mark) {
                Intent I = new Intent(SearchActivity.this, MainActivity.class);
                Toast.makeText(getApplicationContext(), mark.getSnippet(), Toast.LENGTH_SHORT).show();
                startActivity(I);
            }

        });
    }

    public void addMarkers(){
        int k = 1;
        for (Point place: places){
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(place.getPosition().split(",")[0]),
                            Double.parseDouble(place.getPosition().split(",")[1])))
                    .title("Meet up location")).setSnippet(k + ". Meet times: " + place.getHours() + ":" + place.getMinutes() + " " + place.getType());
            k++;
        }

        LatLng latLng = new LatLng(Double.parseDouble(places.get(0).getPosition().split(",")[0]),
                Double.parseDouble(places.get(1).getPosition().split(",")[1]));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));


        CameraPosition cameraPosition = new CameraPosition.Builder( )
                .target(latLng)
                .zoom(13).build();
        //Zoom in and animate the camera.
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public void populatePlaces() throws IOException, JSONException, ExecutionException, InterruptedException, TimeoutException {
        JSONObject json = new GetService().execute().get(1000, TimeUnit.MILLISECONDS);
        JSONArray array = (JSONArray) json.get("places");
        for (int i = 0; i < array.length(); i++){
            places.add((Point) array.get(i));
        }

//        places = new ArrayList<>();
//        places.add("54.899077, 23.935309");
//        places.add("54.896683, 23.935309");
//        places.add("54.900607, 23.945008");
//        places.add("54.904129, 23.949267");
    }
}

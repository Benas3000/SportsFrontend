package com.example.jonas.map;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> Points;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Points = new ArrayList<>();

        Points.add("54.899077, 23.935309;Futbolas;10;24");
        Points.add("54.904129, 23.949267;Krepsinis;12;00");
        Points.add("54.805129, 23.949267;Tenisas;12;00");
        Points.add("54.504129, 23.949267;Regbis;12;00");

    }

    public void searchActivity(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("Points", Points);
        startActivity(intent);
    }

    public void createActivity(View view) {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }
}

package com.example.jonas.map;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class CreateFormActivity extends AppCompatActivity {

    String latLng;

    ExpandableListView SportuListView;
    TextView sportas;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    TextView hoursTextView;
    TextView minutesTextView;

    NumberPicker hourPicker;
    NumberPicker minPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_form);

        latLng = getIntent().getStringExtra("latLng");
//        latLon = new LatLng(Double.parseDouble(temp.split(", ")[0]),
//                Double.parseDouble(temp.split(", ")[1]));
        Log.d("-----------------> ", latLng);

        //TextView
        sportas = (TextView) findViewById(R.id.textView);


        //Number picker
        hourPicker = (NumberPicker) findViewById(R.id.numberPicker2);
        hourPicker.setMaxValue(23);
        hourPicker.setMinValue(0);
        hourPicker.setWrapSelectorWheel(true);

        String[] k = {"0", "15", "30", "45"};
        minPicker = (NumberPicker) findViewById(R.id.numberPicker3);
        minPicker.setDisplayedValues(k);
        //minPicker.setMaxValue(45);
        //minPicker.setMinValue(0);
        minPicker.setWrapSelectorWheel(true);

        //ExpandableList
        expListView = (ExpandableListView) findViewById(R.id.miestuList);
        MiestaiListSetUp();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
        expListView.setOnChildClickListener(new OnChildClickListener() {


            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();

                sportas.setText(listDataChild.get(
                        listDataHeader.get(groupPosition)).get(
                        childPosition));
                sportas.setVisibility(v.VISIBLE);

                return false;
            }
        });
    }

    private void MiestaiListSetUp() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        listDataHeader.add("Tipas");


        List<String> tipas = new ArrayList<String>();
        tipas.add("Futbolas");
        tipas.add("Krepsinis");
        tipas.add("Tenisas");
        tipas.add("Sachmatai");

        listDataChild.put(listDataHeader.get(0), tipas);
    }

    public void submitPoint(View view){
        String hours = new String();
        String minutes = new String();
        try
        {
            //hours = hoursTextView.getText().toString();
            //minutes = minutesTextView.getText().toString();
            //Point p = new Point(latLng, sportas.getText().toString(), Integer.parseInt(hoursTextView.getText().toString()), Integer.parseInt(minutesTextView.getText().toString()));
        }
        catch (Exception ex){Toast.makeText(getApplicationContext(), "Bad time format!", Toast.LENGTH_SHORT).show();}
       // boolean error = false;

        if(Integer.parseInt(hours) > 12 || Integer.parseInt(minutes) > 60 || hours == "" || minutes == ""){
            Toast.makeText(getApplicationContext(), "Bad time format!", Toast.LENGTH_SHORT).show();
            //error = true;
        }
        if(sportas.getText() == "TextView"){
            Toast.makeText(getApplicationContext(), "Select an activity!", Toast.LENGTH_SHORT).show();
           // error = true;
        }

    }

    public void submitForm(View view){
        new PostService().execute(latLng);
    }
}

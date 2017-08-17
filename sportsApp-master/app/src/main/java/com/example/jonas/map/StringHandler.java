package com.example.jonas.map;

import java.util.ArrayList;
import java.util.List;


public class StringHandler {

    public StringHandler(){
    }

    public ArrayList<Point> convertToPoints(ArrayList<String> points) {
        ArrayList<Point> Points = new ArrayList<Point>();

        for (String item : points) {
            String[] values = item.split(";");
            Points.add(new Point(values[0], values[1], Integer.parseInt(values[2]), Integer.parseInt(values[3])));
        }
        return Points;
    }

}

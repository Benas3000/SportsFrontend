package com.example.jonas.map;

public class Point {

    private String position;
    private String type;
    private int hours;
    private int minutes;

    public Point(String position, String type, int hours, int minutes){
        this.position = position;
        this.type = type;
        this.hours = hours;
        this.minutes = minutes;
    }

    public String getPosition(){
        return position;
    }

    public String getType(){
        return  type;
    }

    public int getHours(){
        return hours;
    }

    public int getMinutes(){
        return minutes;
    }
}

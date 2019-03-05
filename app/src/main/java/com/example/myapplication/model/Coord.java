package com.example.myapplication.model;


public class Coord {

    private Double lon;
    private Double lat;

    public Coord(){

    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }


    @Override
    public String toString(){
        return "[" + this.lat + "," + this.lon + "]";
    }


}

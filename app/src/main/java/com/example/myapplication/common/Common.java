package com.example.myapplication.common;


import android.location.Location;

public class Common {



    public static final String API_KEY = "c0bd6ef0f0ce59b92a43277e9db4b463";
    public static Location current_location=null;


    public Common(){}

    public static String fahrenheitToCelsius(double temp){
        return ""+ ((temp-32.0)*(5.0/9.0));
    }

}

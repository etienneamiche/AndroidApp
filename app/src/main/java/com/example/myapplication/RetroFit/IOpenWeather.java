package com.example.myapplication.RetroFit;

import com.example.myapplication.Model.Results;

import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;


public interface IOpenWeather {
    @GET("weather")
    Observable<Results> getWeatherByLatLon(@Query("lat") String lat,
                                     @Query("lon") String lon,
                                     @Query("appid") String appid);


}

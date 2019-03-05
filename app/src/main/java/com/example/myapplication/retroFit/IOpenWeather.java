package com.example.myapplication.retroFit;

import com.example.myapplication.model.ForecastResults;
import com.example.myapplication.model.Results;

import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;


public interface IOpenWeather {
    @GET("weather")
    Observable<Results> getWeatherByLatLon(@Query("lat") String lat,
                                           @Query("lon") String lon,
                                           @Query("appid") String appid,
                                           @Query("units")String units);

    @GET("forecast")
    Observable<ForecastResults> getForecastWeatherByLatLon(@Query("lat") String lat,
                                                           @Query("lon") String lon,
                                                           @Query("appid") String appid,
                                                           @Query("units")String units);



}

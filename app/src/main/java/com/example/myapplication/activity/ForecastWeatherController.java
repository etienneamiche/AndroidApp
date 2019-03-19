package com.example.myapplication.activity;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.myapplication.common.Common;
import com.example.myapplication.model.WeatherForecastResults;
import com.example.myapplication.retroFit.IOpenWeather;
import com.example.myapplication.retroFit.RetroFitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class ForecastWeatherController {

    private CompositeDisposable compositeDisposable;
    private ForecastWeatherFragment fragment;
    private IOpenWeather mService;


    public ForecastWeatherController(ForecastWeatherFragment fragment){
        this.fragment = fragment;
        Retrofit retrofit = RetroFitClient.getInstance();
        mService = retrofit.create(IOpenWeather.class);
        compositeDisposable = new CompositeDisposable();

    }


    public void getForecastWeatherInformation() {
        compositeDisposable.add(mService.getForecastWeatherByLatLon(
                Common.current_location.getLatitude()+"",
                Common.current_location.getLongitude()+"",
                Common.API_KEY,
                "metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherForecastResults>() {
                    @Override
                    public void accept(WeatherForecastResults weatherForecastResults) throws Exception {

                        fragment.displayForecastWeatherResult(weatherForecastResults);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("Error",throwable.getMessage());
                    }
                })
        );

    }
}

package com.example.myapplication.activity;

import com.example.myapplication.common.Common;
import com.example.myapplication.model.Results;
import com.example.myapplication.retroFit.IOpenWeather;
import com.example.myapplication.retroFit.RetroFitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class TodayWeatherController {

    private TodayWeatherFragment fragment;
    private CompositeDisposable compositeDisposable;
    private IOpenWeather weatherService;

    public TodayWeatherController(TodayWeatherFragment fragment){
        this.fragment = fragment;
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetroFitClient.getInstance();
        weatherService = retrofit.create(IOpenWeather.class);
    }
    public void getWeatherInformation() {

        compositeDisposable.add(weatherService.getWeatherByLatLon(String.valueOf(Common.current_location.getLatitude()),
                String.valueOf(Common.current_location.getLongitude()),
                Common.API_KEY,
                "metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Results>() {
                    @Override
                    public void accept(Results results) throws Exception {
                        fragment.showData(results);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        fragment.showError(throwable);
                    }
                })

        );
    }



}

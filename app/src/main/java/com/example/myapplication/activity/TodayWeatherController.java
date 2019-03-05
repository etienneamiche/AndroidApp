package com.example.myapplication.activity;

import com.example.myapplication.common.Common;
import com.example.myapplication.model.Results;
import com.example.myapplication.retroFit.IOpenWeather;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class TodayWeatherController {

    private TodayWeather fragment;
    public TodayWeatherController(TodayWeather fragment){
        this.fragment = fragment;
    }
    private CompositeDisposable compositeDisposable;
    private IOpenWeather weatherService;

    private void getWeatherInformation() {

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

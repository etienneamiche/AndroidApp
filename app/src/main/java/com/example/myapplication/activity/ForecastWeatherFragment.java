package com.example.myapplication.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.WeatherForecastAdapter;
import com.example.myapplication.common.Common;
import com.example.myapplication.model.WeatherForecastResults;
import com.example.myapplication.retroFit.IOpenWeather;
import com.example.myapplication.retroFit.RetroFitClient;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class ForecastWeatherFragment extends Fragment {
    static ForecastWeatherFragment instance;

    CompositeDisposable compositeDisposable;
    IOpenWeather mService;

    RecyclerView recycler_forecast;
    public ForecastWeatherFragment() {
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetroFitClient.getInstance();
        mService = retrofit.create(IOpenWeather.class);

    }


    public static ForecastWeatherFragment getInstance(){
        if (instance == null)
            instance = new ForecastWeatherFragment();
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView =  inflater.inflate(R.layout.fragment_forecast_weather, container, false);

        recycler_forecast = itemView.findViewById(R.id.forcast_recycler);
        recycler_forecast.setHasFixedSize(true);
        recycler_forecast.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL , false));

        getForecastWeatherInformation();
        return itemView;
    }

    private void getForecastWeatherInformation() {
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
                        
                        displayForecastWeatherResult(weatherForecastResults);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("Error",throwable.getMessage());
                    }
                })
        );

    }

    private void displayForecastWeatherResult(WeatherForecastResults weatherForecastResults) {

        WeatherForecastAdapter adapter = new WeatherForecastAdapter(getContext(),weatherForecastResults);

        recycler_forecast.setAdapter(adapter);
    }


}

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

    ForecastWeatherController controller;
    RecyclerView recycler_forecast;
    public ForecastWeatherFragment() {
        controller = new ForecastWeatherController(this);

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

        controller.getForecastWeatherInformation();
        return itemView;
    }



    public void displayForecastWeatherResult(WeatherForecastResults weatherForecastResults) {

        WeatherForecastAdapter adapter = new WeatherForecastAdapter(getContext(),weatherForecastResults);

        recycler_forecast.setAdapter(adapter);
    }


}

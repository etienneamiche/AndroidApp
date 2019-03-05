package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.emredavarci.circleprogressbar.CircleProgressBar;
import com.example.myapplication.common.Common;
import com.example.myapplication.model.Results;
import com.example.myapplication.R;
import com.example.myapplication.retroFit.IOpenWeather;
import com.example.myapplication.retroFit.RetroFitClient;
import com.squareup.picasso.Picasso;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class TodayWeather extends Fragment implements SwipeRefreshLayout.OnRefreshListener {



    private ImageView img_weather_icon,img_wind_icon;
    private TextView txt_city_name,txt_current_temperature,txt_coords,txt_description,txt_wind_speed,txt_wind_deg;
    private CircleProgressBar progress_humidity;
    private SwipeRefreshLayout current_weather_panel;
    private CompositeDisposable compositeDisposable;
    private IOpenWeather weatherService;
    private RotateAnimation r;

    private TodayWeatherController controller;

    private static TodayWeather instance;




    public static TodayWeather getInstance() {
        if(instance == null) {
            instance = new TodayWeather();
        }
        return instance;
    }

    public TodayWeather() {

        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetroFitClient.getInstance();
        weatherService = retrofit.create(IOpenWeather.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_today_weather, container, false);


        img_weather_icon = itemView.findViewById(R.id.img_weather_icon);
        txt_city_name = itemView.findViewById(R.id.txt_city_name);
        txt_current_temperature = itemView.findViewById(R.id.txt_current_temperature);
        txt_coords = itemView.findViewById(R.id.txt_coords);
        txt_description = itemView.findViewById(R.id.txt_description);
        txt_wind_speed =itemView.findViewById(R.id.txt_wind_speed);
        txt_wind_deg = itemView.findViewById(R.id.txt_wind_deg);
        progress_humidity = itemView.findViewById(R.id.progress_humidity);
        img_wind_icon = itemView.findViewById(R.id.img_wind_icon);
        current_weather_panel = itemView.findViewById(R.id.current_weather_panel);
        current_weather_panel.setOnRefreshListener(this);




        getWeatherInformation();

        r =new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        r.setInterpolator(new LinearInterpolator());
        r.setDuration(2000);
        r.setRepeatCount(Animation.INFINITE);
        img_wind_icon.startAnimation(r);
        return itemView;
    }

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
                                   showData(results);
                               }
                           }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showError(throwable);
                    }
                })

        );
    }

    public void showError(Throwable throwable) {
        Log.e("Error",throwable.getMessage());
        Toast.makeText(getActivity(), ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public void showData(Results results) {
        //Get Image;
        Picasso.get().load("https://openweathermap.org/img/w/"+results.getWeather().get(0).getIcon()+".png").into(img_weather_icon);
        //Get Field;


        txt_city_name.setText(results.getName());
        txt_coords.setText(results.getCoord().toString());
        txt_current_temperature.setText(results.getMain().getTemp()+"°C");
        txt_description.setText(results.getWeather().get(0).getDescription());
        progress_humidity.setProgress(results.getMain().getHumidity());
        progress_humidity.setText(""+results.getMain().getHumidity());
        txt_wind_speed.setText("Speed : "+results.getWind().getSpeed());
        txt_wind_deg.setText("Deg : "+results.getWind().getDeg());
        //Enable Panel
        current_weather_panel.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh(){
        getWeatherInformation();
        current_weather_panel.setRefreshing(false);
    }

}

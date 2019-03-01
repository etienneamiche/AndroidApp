package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Common.Common;
import com.example.myapplication.Model.Results;
import com.example.myapplication.RetroFit.IOpenWeather;
import com.example.myapplication.RetroFit.RetroFitClient;
import com.squareup.picasso.Picasso;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class TodayWeather extends Fragment {



    private ImageView img_weather_icon;
    private TextView txt_city_name,txt_current_temperature,txt_coords,txt_description;
    private RelativeLayout current_weather_panel;
    private CompositeDisposable compositeDisposable;
    private IOpenWeather weatherService;

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


        current_weather_panel = itemView.findViewById(R.id.current_weather_panel);


        getWeatherInformation();


        return itemView;
    }

    private void getWeatherInformation() {

        compositeDisposable.add(weatherService.getWeatherByLatLon(String.valueOf(Common.current_location.getLatitude()),
                String.valueOf(Common.current_location.getLongitude()),
                Common.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Results>() {
                               @Override
                               public void accept(Results results) throws Exception {


                                   //Get Image;
                                   Picasso.get().load("https://openweathermap.org/img/w/"+results.getWeather().get(0).getIcon()+".png").into(img_weather_icon);
                                   //Get Field;

                                   txt_city_name.setText(results.getName());
                                   txt_coords.setText(results.getCoord().toString());
                                   txt_current_temperature.setText(Common.fahrenheitToCelsius(results.getMain().getTemp())+"°C");
                                   txt_description.setText(results.getWeather().get(0).getDescription());

                                   //Enable Panel
                                   current_weather_panel.setVisibility(View.VISIBLE);

                               }
                           }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("Error",throwable.getMessage());
                        Toast.makeText(getActivity(), ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })

        );
    }


}

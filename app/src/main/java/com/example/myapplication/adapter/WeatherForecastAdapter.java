package com.example.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.common.Common;
import com.example.myapplication.model.WeatherForecastResults;
import com.squareup.picasso.Picasso;

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.MyViewHolder> {
    Context context;
    WeatherForecastResults weatherForecastResults;

    public WeatherForecastAdapter(Context context, WeatherForecastResults weatherForecastResults) {
        this.context = context;
        this.weatherForecastResults = weatherForecastResults;
    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.weather_card,viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Picasso.get().load("https://openweathermap.org/img/w/"+
                weatherForecastResults.getList().get(i).getWeather().get(0).getIcon()+
                ".png").into(myViewHolder.img_icon_weather);

        myViewHolder.txt_date.setText(Common.conertUnixToDate(weatherForecastResults.getList().get(i).getDt()));

        myViewHolder.txt_description.setText(weatherForecastResults.getList().get(i).getWeather().get(0).getDescription());

        myViewHolder.txt_temperature.setText(weatherForecastResults.getList().get(i).getMain().getTemp().toString()+"°C");

    }

    @Override
    public int getItemCount() {
        return weatherForecastResults.getList().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txt_date,txt_temperature,txt_description;
        ImageView img_icon_weather;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img_icon_weather = itemView.findViewById(R.id.img_weather_icon);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_temperature = itemView.findViewById(R.id.txt_temperature);
            txt_description = itemView.findViewById(R.id.txt_description);

        }
    }
}

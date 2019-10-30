package com.example.coolweather.gson;

import com.google.gson.annotations.SerializedName;

public class Forecast {

    @SerializedName("tmp")
    public Temperature temperature;

    @SerializedName("cond")
    public More more;

    public String date;


    public class Temperature {

        public String max;

        public String min;
    }


    public class More {

        @SerializedName("txt_d")
        public String info;
    }
}

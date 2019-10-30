package com.example.coolweather.util;

import android.text.TextUtils;

import com.example.coolweather.db.City;
import com.example.coolweather.db.County;
import com.example.coolweather.db.Province;
import com.google.gson.JsonIOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    /*解析省级数据*/
    public static boolean handleProviceResponse(String response) {
        if (TextUtils.isEmpty(response)) {
            return false;
        }

        try {
            JSONArray allProvinces = new JSONArray(response);
            for (int i = 0; i < allProvinces.length(); i++) {
                JSONObject jsonObject = allProvinces.getJSONObject(i);
                Province province = new Province();
                province.setProvinceName(jsonObject.getString("name"));
                province.setProvinceCode(jsonObject.getInt("id"));
                province.save();
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*解析市级数据*/
    public static boolean handleCityResponse(String response, int provinceId) {
        if (TextUtils.isEmpty(response)) {
            return false;
        }

        try {
            JSONArray allCities = new JSONArray(response);
            for (int i = 0; i < allCities.length(); i++) {
                JSONObject jsonObject = allCities.getJSONObject(i);
                City city = new City();
                city.setProviceId(provinceId);
                city.setCityCode(jsonObject.getInt("id"));
                city.setCityName(jsonObject.getString("name"));
                city.save();
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }

    /*解析县级数据*/
    public static boolean handleCountyResponse(String response, int cityId) {
        if (TextUtils.isEmpty(response)) {
            return false;
        }

        try {
            JSONArray allCounties = new JSONArray(response);
            for (int i = 0; i < allCounties.length(); i++) {
                JSONObject jsonObject = allCounties.getJSONObject(i);
                County county = new County();
                county.setCountyName(jsonObject.getString("name"));
                county.setWeatherId(jsonObject.getString("weather_id"));
                county.setCityId(cityId);
                county.save();
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }
}

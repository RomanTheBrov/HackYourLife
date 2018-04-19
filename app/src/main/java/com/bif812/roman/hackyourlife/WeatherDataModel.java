package com.bif812.roman.hackyourlife;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class which works through a weatherData object and updates so that
 *
 */

public class WeatherDataModel {


    private String mTemperature;
    private String mCity;
    private String mIconName;
    private int mCondition;

    /**
     * Gets data from JSON
     * @param jsonObject which is acquired from the OpenWeatherMap API, will contain data
     *                   like city, weather condition, etc.
     * @return weatherData object which contains condition, icon name and city
     */
    public static WeatherDataModel fromJSON(JSONObject jsonObject){


        try {
            WeatherDataModel weatherData = new WeatherDataModel();

            weatherData.mCity = jsonObject.getString("name");
            weatherData.mCondition = jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");
            weatherData.mIconName = updateWeatherIcon(weatherData.mCondition);

            double tempResult = jsonObject.getJSONObject("main").getDouble("temp") - 273.15;

            //convert double to nearest integer
            int roundedValue = (int) Math.rint(tempResult);

            //to put into textView, need to convert int to string
            weatherData.mTemperature = Integer.toString(roundedValue);

            return weatherData;
        } catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method that updates the icon for weather from the drawable list
     * @param condition based on a list of range of conditions acquired from api
     * @return icon link
     */
    // update image depending on weather condition
    private static String updateWeatherIcon(int condition) {

        if (condition >= 0 && condition < 300) {
            return "tstorm1";
        } else if (condition >= 300 && condition < 500) {
            return "light_rain";
        } else if (condition >= 500 && condition < 600) {
            return "shower3";
        } else if (condition >= 600 && condition <= 700) {
            return "snow4";
        } else if (condition >= 701 && condition <= 771) {
            return "fog";
        } else if (condition >= 772 && condition < 800) {
            return "tstorm3";
        } else if (condition == 800) {
            return "sunny";
        } else if (condition >= 801 && condition <= 804) {
            return "cloudy2";
        } else if (condition >= 900 && condition <= 902) {
            return "tstorm3";
        } else if (condition == 903) {
            return "snow5";
        } else if (condition == 904) {
            return "sunny";
        } else if (condition >= 905 && condition <= 1000) {
            return "tstorm3";
        }

        return "dunno";
    }


    /**
     * Getter method for temperature
     * @return mTemperature temperature good enough for display
     */
    public String getTemperature() {
        return mTemperature + "°";
    }

    /**
     * Getter method for city name
     * @return mCity name good enough for display
     */
    public String getCity() {
        return mCity;
    }

    /**
     * Getter method for icon
     * @return mIconName icon name translated for display
     */
    public String getIconName() {
        return mIconName;
    }
}

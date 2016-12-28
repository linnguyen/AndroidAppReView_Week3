package data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Util.Utils;
import model.Place;
import model.Weather;

/**
 * Created by lin on 26/12/2016.
 */

public class JSONweatherParser {
    public static Weather getWeather(String data){
        Weather weather = new Weather();
        //create JsonObject from data
        try {
            JSONObject jsonObject = new JSONObject(data);
            Place place = new Place();
            JSONObject coordObj = Utils.getObject("coord", jsonObject);
            place.setLat(Utils.getFloat("lat", coordObj));
            place.setLon(Utils.getFloat("lon", coordObj));
            // Get sys object
            JSONObject sysObj = Utils.getObject("sys" ,jsonObject);
            place.setCountry(Utils.getString("country", sysObj));
            place.setSunrise((long) Utils.getInt("sunrise", sysObj));
            place.setSunset((long) Utils.getInt("sunset",sysObj));
            place.setCountry(Utils.getString("country", sysObj));
            weather.place = place;
            //Get the weather infor
            JSONArray jsonArray = jsonObject.getJSONArray("weather");
            JSONObject jsonWeather = jsonArray.getJSONObject(0);
            weather.currentCondition.setWeatherId(Utils.getInt("id", jsonWeather));
            weather.currentCondition.setCondition(Utils.getString("main", jsonWeather));
            weather.currentCondition.setDescription(Utils.getString("description", jsonWeather));
            weather.currentCondition.setIcon(Utils.getString("icon", jsonWeather));
            //get wind
            JSONObject windObj = Utils.getObject("wind", jsonObject);
            weather.wind.setSpeed(Utils.getFloat("speed", windObj));
            //get cloud
            JSONObject cloudObj = Utils.getObject("clouds", jsonObject);
            weather.clouds.setPrecipitation(Utils.getInt("all",cloudObj));
            //get city
            weather.place.setCity(Utils.getString("name",jsonObject));
            //get main object
            JSONObject mainObj = Utils.getObject("main", jsonObject);
            weather.currentCondition.setHumidity(Utils.getInt("humidity", mainObj));
            weather.currentCondition.setPressure(Utils.getInt("pressure", mainObj));
            weather.currentCondition.setMinTemp(Utils.getFloat("temp_min", mainObj));
            weather.currentCondition.setMaxTemp(Utils.getFloat("temp_max", mainObj));
            weather.currentCondition.setTemperature(Utils.getDouble("temp", mainObj));
            //Log.v("Country: ",city.toString());

            return weather;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

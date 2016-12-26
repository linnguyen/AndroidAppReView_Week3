package data;

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
            JSONObject coordObj = Utils.getObject("coo")
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

package Util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lin on 26/12/2016.
 */

public class Utils {
    public static final String APPID = "be8d3e323de722ff78208a7dbb2dcd6f";
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    public static final String ICON_URL = "http://openweathermap.org/img/w";
    public static JSONObject getObject(String tagName, JSONObject jsonObject) throws JSONException{
        JSONObject jObj = jsonObject.getJSONObject(tagName);
        return jObj;
    }
    public static String getString(String tagName, JSONObject jsonObject) throws  JSONException{
        return jsonObject.getString(tagName);
    }
    public static float getFloat(String tagName, JSONObject jsonObject) throws JSONException{
        return (float) jsonObject.getDouble(tagName);
    }
    public static float getDouble(String tagName, JSONObject jsonObject) throws JSONException{
        return (float) jsonObject.getDouble(tagName);
    }
    public static float getInt(String tagName, JSONObject jsonObject) throws JSONException{
        return (int) jsonObject.getInt(tagName);
    }
}

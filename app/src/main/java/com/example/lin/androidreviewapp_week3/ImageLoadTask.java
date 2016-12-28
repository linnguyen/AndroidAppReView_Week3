package com.example.lin.androidreviewapp_week3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.*;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import Util.Utils;
import data.JSONweatherParser;
import model.Weather;

/**
 * Created by lin on 28/12/2016.
 */

public class ImageLoadTask extends AsyncTask<String, Void, Bitmap> {
    private Weather weather = new Weather();
    private ImageView imageView;
    public ImageLoadTask(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String data = HttpHandler.makeServiceCall(params[0]);
        //Log.v("Json Day ne: ", data);
        weather = JSONweatherParser.getWeather(data);
        // Log.v("Country", weather.place.getCountry());
        try {
            URL urlConection = new URL(Utils.ICON_URL +"/"+weather.currentCondition.getIcon()+".png");
            HttpURLConnection connection = (HttpURLConnection) urlConection.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
            return myBitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

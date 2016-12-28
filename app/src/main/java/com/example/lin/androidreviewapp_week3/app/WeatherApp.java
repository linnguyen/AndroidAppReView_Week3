package com.example.lin.androidreviewapp_week3.app;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lin.androidreviewapp_week3.HttpHandler;
import com.example.lin.androidreviewapp_week3.ImageLoadTask;
import com.example.lin.androidreviewapp_week3.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

import data.JSONweatherParser;
import model.Weather;

public class WeatherApp extends AppCompatActivity {
    private TextView cityName;
    private TextView temp;
    private ImageView iconView;
    private TextView description;
    private TextView humidity;
    private TextView pressure;
    private TextView wind;
    private TextView sunrise;
    private TextView sunset;
    private TextView updated;

    final Context context = this;
    private Weather weather = new Weather();
    private String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_app);

        cityName = (TextView) findViewById(R.id.cityText);
        temp = (TextView) findViewById(R.id.tempText);
        iconView = (ImageView) findViewById(R.id.thumbnailIcon);
        description = (TextView) findViewById(R.id.cloudText);
        humidity = (TextView) findViewById(R.id.humidityText);
        pressure = (TextView) findViewById(R.id.pressureText);
        wind = (TextView) findViewById(R.id.windText);
        sunrise = (TextView) findViewById(R.id.riseText);
        sunset = (TextView) findViewById(R.id.setText);
        updated = (TextView) findViewById(R.id.updateText);

        //Toast.makeText(getApplicationContext(), city,Toast.LENGTH_LONG).show();
        renderWeatherData(city);
    }
    public void renderWeatherData(String city){
        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(new String[]{city});
       // String urlImage = "http://openweathermap.org/img/w/"+weather.iconData +".png";
      //  Log.e("linimage: ",weather.currentCondition.getCondition()+ "djdjd");
        ImageLoadTask imageLoadTask = new ImageLoadTask(iconView);
        imageLoadTask.execute(new String[]{city});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //item.getItemId()
        //Toast.makeText(getApplicationContext(), "item ne", Toast.LENGTH_LONG).show();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View dialogView = layoutInflater.inflate(R.layout.custom_dialog, null);
        AlertDialog.Builder alertDBuilder = new AlertDialog.Builder(context);
        //set title
        //alertDBuilder.setTitle("Please fill in location'name!");
        //set custom dialog icon
        alertDBuilder.setIcon(R.mipmap.city);
        alertDBuilder.setView(dialogView);
        final EditText userInput = (EditText) dialogView.findViewById(R.id.et_input);
        //set dialog message
        alertDBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //get input city here
                                city = userInput.getText().toString();
                                renderWeatherData(city);
                               // onRestart();
                               //onCreate(new Bundle());
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
        //create alert dialog
        AlertDialog alertDialog = alertDBuilder.create();
        //show it
        alertDialog.show();
        return super.onOptionsItemSelected(item);
    }

    private class WeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);
            DateFormat dateFormat = DateFormat.getTimeInstance();
            String timeSunRise = dateFormat.format(new Date(weather.place.getSunrise()));
            String sunsetDate = dateFormat.format(new Date(weather.place.getSunset()));
            String updateDate = dateFormat.format(new Date(weather.place.getLastupdate()));

            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            String tempFormat = decimalFormat.format(weather.currentCondition.getTemperature());

            cityName.setText(weather.place.getCity()+ "," +weather.place.getCountry());
            temp.setText(""+ tempFormat + "°F ");
            humidity.setText("Humidity: "+weather.currentCondition.getHumidity()+ "%");
            pressure.setText("Pressure: "+weather.currentCondition.getPressure()+ "hPa");
            wind.setText("Wind: "+weather.wind.getSpeed()+ "mps");
            sunrise.setText("Sunrise: "+ timeSunRise);
            sunset.setText("Sunset: "+ sunsetDate);
            updated.setText("Lasted Updated: "+ updateDate);
            description.setText("Condition: "+weather.currentCondition.getCondition() +"("+
                    weather.currentCondition.getDescription()+ ")");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Weather doInBackground(String... params) {
            String data = HttpHandler.makeServiceCall(params[0]);
            //Log.v("Json Day ne: ", data);
            weather = JSONweatherParser.getWeather(data);
            // Log.v("Country", weather.place.getCountry());
            return weather;
        }
    }


}

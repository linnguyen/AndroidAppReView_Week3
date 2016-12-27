package com.example.lin.androidreviewapp_week3.app;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lin.androidreviewapp_week3.HttpHandler;
import com.example.lin.androidreviewapp_week3.R;

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

    Weather weather = new Weather();
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

        renderWeatherData("hồ chí minh");
    }
    public void renderWeatherData(String city){
        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(new String[]{city});
    }
    private class DownloadImageAsyncTask extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
        private Bitmap downloadImage(String code) {
            //Bita
            return null;
        }
    }
    private class WeatherTask extends AsyncTask<String, Void, Weather>{

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }
}

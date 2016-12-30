package com.example.lin.androidreviewapp_week3;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lin.androidreviewapp_week3.app.WeatherApp;

import Util.Utils;



public class MainActivity extends AppCompatActivity {
    Intent intent;

    ListView lvTopic;
    TextView tvTopic;
    ArrayAdapter<String> arrayAdapter;
    String [] arrTopic = {"Thread", "Webservice", "Asynctask", "Weather app", "Google Map Apps"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvent();

    }

    private void addEvent() {
        lvTopic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                       // Intent intent = new Intent(MainActivity.this,)
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this,Json.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, AsyncTask.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, WeatherApp.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, GoogleMapsApp.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
    private void addControls(){
        lvTopic =(ListView)findViewById(R.id.lvTopic);
        tvTopic =(TextView) findViewById(R.id.tvTopPic);
        arrayAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.row, R.id.tvTopPic, arrTopic);
        lvTopic.setAdapter(arrayAdapter);
    }


}

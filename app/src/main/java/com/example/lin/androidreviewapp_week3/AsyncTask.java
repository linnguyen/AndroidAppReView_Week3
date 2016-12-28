package com.example.lin.androidreviewapp_week3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class AsyncTask extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        imageView = (ImageView) findViewById(R.id.imageView);

        new ImageLoadTask(imageView).execute();
    }
}

package com.example.itaykan.service1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // Start the service
    public void startService(View view) {
        startService(new Intent(this, SampleService.class));
    }
    // Stop the service
    public void stopService(View view) {
        stopService(new Intent(this, SampleService.class));
    }
}
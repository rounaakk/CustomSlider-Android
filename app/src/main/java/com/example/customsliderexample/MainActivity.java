package com.example.customsliderexample;

import android.os.Bundle;

import com.example.customslider.CustomSlider;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    CustomSlider customSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize the slider and set input values
        customSlider = findViewById(R.id.customSlider1);
        customSlider.setValues(0, 40000, 6000, 20000, 12000, 300);
    }
}
package com.example.customsliderexample;

import android.os.Bundle;

import com.example.customslider.CustomInfoSlider;
import com.example.customslider.CustomSlider;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    CustomSlider customSlider1, customSlider2;
    CustomInfoSlider customInfoSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Slider with 3 regions (low, normal,high) | Use SetValues method for that
        customSlider1 = findViewById(R.id.customSlider1);
        customSlider1.setValues(0, 40000, 20000, 30000, 30000, 300);

        /* Slider with unlimited regions
         result will be set irrespective of weight, so it works only when all the weight are equal
         result = no. of block for res starting from 1 */

        customSlider2 = findViewById(R.id.customSlider2);
        customSlider2.setMultipleValues(4, new String[]{"#7596ff", "#7565ff", "#759685", "#829712", "#759325"}, new float[]{25, 25, 25, 25}, 3, 300);


        //CustomInfoSlider
        /* Slider with unlimited regions and their texts
         result will be set irrespective of weight, so it works only when all the weight are equal
         result = no. of block for res starting from 1 */

        customInfoSlider = findViewById(R.id.customInfoSlider);
        customInfoSlider.setMultipleInfoValues(5, new String[]{"#7596ff", "#7565ff", "#759685", "#829712", "#125625"}, new String[]{"(-) Negative", "(+-) 15 Cells/µl", "(+1) 70 Cells/µl", "(+2) 125 Cells/µl", "(+3) 500 Cells/µl"}, new float[]{20, 20, 20, 20, 20}, 3, 300);


    }


}
package com.example.customslider;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

public class CustomSlider extends ConstraintLayout {


    LinearLayout sliderLayout;

    private ImageView firstBlock, secondBlock, thirdBlock, loc;
    private TextView tv_min, tv_max, tv_res;


    public CustomSlider(Context context) {
        super(context);
        init();
    }

    public CustomSlider(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomSlider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {

        LayoutInflater.from(getContext()).inflate(R.layout.slider_widget, this);
        loc = findViewById(R.id.loc);

        tv_max = findViewById(R.id.tv_max);
        tv_min = findViewById(R.id.tv_min);
        tv_res = findViewById(R.id.tv_res);

        sliderLayout = findViewById(R.id.sliderLinearLayout);
        sliderLayout.setOrientation(LinearLayout.HORIZONTAL);

    }


    public void setValues(float min, float max, float normalMin, float normalMax, float res, int sliderWholeLayoutWidth) {


        //creating imageviews
        ImageView firstBlock = new ImageView(getContext());
        ImageView secondBlock = new ImageView(getContext());
        ImageView thirdBlock = new ImageView(getContext());


        //fb is firstBlock and Likewise
        float fbWeight = (normalMin * 100) / (max - min);
        float sbWeight = ((normalMax - normalMin) * 100) / (max - min);
        float tbWeight = 100 - fbWeight - sbWeight;

        float resPos = (res * 100) / (max - min);
        float resposInDp = ((resPos * (sliderWholeLayoutWidth - 40)) / 100) + 10;
        int resposInPx = (int) (resposInDp * Resources.getSystem().getDisplayMetrics().density);

        Log.i("resp", String.valueOf(resPos));
        Log.i("respDP", String.valueOf(resposInDp));
        Log.i("respPX", String.valueOf(resposInPx));

        /*Check if i/p is float or int
          also if it is overlapping min/max text
          remove after 20% left both sides
          and display accordingly
        */

        if (resPos <= 80) {
            if (Math.round(max) == max)
                tv_max.setText(String.valueOf((int) max));
            else
                tv_max.setText(String.valueOf(max));
        } else {
            tv_max.setAlpha(0f);
        }

        if (resPos >= 20) {
            if (Math.round(min) == min)
                tv_min.setText(String.valueOf((int) min));
            else
                tv_min.setText(String.valueOf(min));
        } else {
            tv_min.setAlpha(0f);
        }

        if (Math.round(res) == res)
            tv_res.setText(String.valueOf((int) res));
        else
            tv_res.setText(String.valueOf(res));


        //Changing color of loc
        if (resPos >= (fbWeight + sbWeight)) {
            loc.setColorFilter(Color.argb(225, 225, 0, 0), PorterDuff.Mode.SRC_IN);
        } else if (resPos >= fbWeight && resPos < (fbWeight + sbWeight)) {
            loc.setColorFilter(Color.argb(225, 0, 0, 255), PorterDuff.Mode.SRC_IN);
        } else {
            loc.setColorFilter(Color.argb(225, 255, 255, 0), PorterDuff.Mode.SRC_IN);

        }

        sliderLayout.setWeightSum(100f);
        LayoutParams lp = (LayoutParams) loc.getLayoutParams();
        lp.leftMargin = resposInPx;

        loc.setLayoutParams(lp);

        firstBlock.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, fbWeight));
        secondBlock.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, sbWeight));
        thirdBlock.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, tbWeight));

        firstBlock.setBackgroundColor(Color.YELLOW);
        secondBlock.setBackgroundColor(Color.BLUE);
        thirdBlock.setBackgroundColor(Color.RED);

        sliderLayout.addView(firstBlock);
        sliderLayout.addView(secondBlock);
        sliderLayout.addView(thirdBlock);
    }


    public void setMultipleValues(int numberOfRegions, String[] colors, float[] weightOutOf100, int result, int sliderWholeLayoutWidth) {

        try {

            sliderLayout.setWeightSum(100f);
            sliderLayout.setOrientation(LinearLayout.HORIZONTAL);


            //Inflating ImageViews and setting colors
            for (int i = 0; i < numberOfRegions; i++) {
                ImageView imgView = new ImageView(getContext());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, weightOutOf100[i]);
                imgView.setLayoutParams(lp);
                imgView.setBackgroundColor(Color.parseColor(colors[i]));
                sliderLayout.addView(imgView);
            }

            float res = (float) ((100 / numberOfRegions) * (result - 0.5));
            float resItr = res;

            int k = 0;
            while (resItr > 0) {
                resItr = resItr - weightOutOf100[k];
                k++;
            }
            loc.setColorFilter(Color.parseColor(colors[k - 1]));

            //res setup
            float resposInDp = ((res * (sliderWholeLayoutWidth - 40)) / 100) + 10;
            int resposInPx = (int) (resposInDp * Resources.getSystem().getDisplayMetrics().density);


            Log.i("resposinpx", String.valueOf(resposInPx));
            Log.i("resposindp", String.valueOf(resposInDp));
            Log.i("resposinres", String.valueOf(res));

            LayoutParams lp = (LayoutParams) loc.getLayoutParams();
            lp.leftMargin = resposInPx;
            loc.setLayoutParams(lp);
        } catch (Exception e) {
            Log.e("ERROR CAUSE", " Check the parameters of setMultipleValues ");
            Toast.makeText(getContext(), "ERROR : Check the parameters of setMultipleValues", Toast.LENGTH_LONG).show();
        }

    }
}
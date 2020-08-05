package com.example.customslider;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class CustomSlider extends ConstraintLayout {

    float min, max, normalMin, normalMax, res;
    LinearLayout sliderLayout;
    int sl_width;
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
        firstBlock = findViewById(R.id.firstBlock);
        secondBlock = findViewById(R.id.secondBlock);
        thirdBlock = findViewById(R.id.thirdBlock);
        loc = findViewById(R.id.loc);

        tv_max = findViewById(R.id.tv_max);
        tv_min = findViewById(R.id.tv_min);
        tv_res = findViewById(R.id.tv_res);

        sliderLayout = findViewById(R.id.sliderLinearLayout);
        sliderLayout.setOrientation(LinearLayout.HORIZONTAL);

    }


    public void setValues(float min, float max, float normalMin, float normalMax, float res, int sliderWholeLayoutWidth) {
        this.min = min;
        this.max = max;
        this.normalMin = normalMin;
        this.normalMax = normalMax;
        this.res = res;
        this.sl_width = sliderWholeLayoutWidth;

        //fb is firstBlock and Likewise
        float fbWeight = (normalMin * 100) / (max - min);
        float sbWeight = ((normalMax - normalMin) * 100) / (max - min);
        float tbWeight = 100 - fbWeight - sbWeight;

        float resPos = (res * 100) / (max - min);

        float resposInDp = ((resPos * (sl_width - 40)) / 100) + 10;
        int resposInPx = (int) (resposInDp * Resources.getSystem().getDisplayMetrics().density);


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
//        if (resPos>=(fbWeight+sbWeight)){
//            loc.setColorFilter(Color.argb(225,225,0,0), PorterDuff.Mode.SRC_IN );
//        }

//        Log.i("weight", String.valueOf(fbWeight));
//        Log.i("weight", String.valueOf(sbWeight));
//        Log.i("weight", String.valueOf(resPos));
        sliderLayout.setWeightSum(100f);


        LayoutParams lp = (LayoutParams) loc.getLayoutParams();
        lp.leftMargin = resposInPx;

        loc.setLayoutParams(lp);

        firstBlock.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, fbWeight));
        secondBlock.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, sbWeight));
        thirdBlock.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, tbWeight));


//        double sl_width= sliderLayout.getWidth();
        Log.i("slwidth", String.valueOf(sl_width));

    }


}

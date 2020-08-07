package com.example.customslider;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class CustomInfoSlider extends ConstraintLayout {

    LinearLayout sliderLayout, infoLayout;
    private ImageView loc;


    public CustomInfoSlider(Context context) {
        super(context);
        init();
    }

    public CustomInfoSlider(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomInfoSlider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {

        LayoutInflater.from(getContext()).inflate(R.layout.slider_widget_with_description, this);
        loc = findViewById(R.id.loc);

        sliderLayout = findViewById(R.id.sliderLinearLayout);
        sliderLayout.setOrientation(LinearLayout.HORIZONTAL);


        infoLayout = findViewById(R.id.infoLinearLayout);
        sliderLayout.setOrientation(LinearLayout.HORIZONTAL);
    }


    public void setMultipleInfoValues(int numberOfRegions, String[] colors, String[] refValues, float[] weightOutOf100, int result, int sliderWholeLayoutWidth) {

//        try {

        sliderLayout.setWeightSum(100f);
        sliderLayout.setOrientation(LinearLayout.HORIZONTAL);

        infoLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        infoLayout.setDividerDrawable(getContext().getResources().getDrawable(R.drawable.divider_drawable));


        //Inflating ImageViews and setting colors
        for (int i = 0; i < numberOfRegions; i++) {
            ImageView imgView = new ImageView(getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, weightOutOf100[i]);
            imgView.setLayoutParams(lp);
            imgView.setBackgroundColor(Color.parseColor(colors[i]));
            sliderLayout.addView(imgView);

            TextView textView = new TextView(getContext());
            textView.setLayoutParams(lp);
            textView.setText(refValues[i]);
            textView.setPadding(5, 5, 5, 5);
            textView.setGravity(Gravity.CENTER);
            infoLayout.addView(textView);

            if (result == i + 1) {
                textView.setTextColor(Color.parseColor(colors[i]));
            }

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

        //Adding Info


//        } catch (Exception e) {
//            Log.e("ERROR CAUSE", " Check the parameters of setMultipleValues ");
//            Toast.makeText(getContext(), "ERROR : Check the parameters of setMultipleValues", Toast.LENGTH_LONG).show();
//        }

    }

}


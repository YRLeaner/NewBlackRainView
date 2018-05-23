package com.example.tyr.blackhackerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;

/**
 * Created by tyr on 2018/5/21.
 */

public class NumberRain extends LinearLayout {

    private int normalColor = Color.GREEN;
    private int hightLightColor = Color.YELLOW;
    private float textSize = 15 * getResources().getDisplayMetrics().density;
    private boolean draw = false;
    public NumberRain(Context context) {
        this(context,null);
    }

    public NumberRain(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NumberRain(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            parseAttrs(attrs);
        }
        initNormal();
    }

    private void initNormal() {
        setOrientation(HORIZONTAL);
        setBackgroundColor(Color.BLACK);
    }

    private void parseAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.NumberRain);
        normalColor = typedArray.getColor(R.styleable.NumberRain_normalColor, Color.GREEN);
        hightLightColor = typedArray.getColor(R.styleable.NumberRain_highLightColor, Color.RED);
        textSize = typedArray.getDimension(R.styleable.NumberRain_textSize, textSize);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!draw){
            draw = true;
            addRainItems();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void addRainItems() {
        int count = (int) (getMeasuredWidth() / textSize);
        Log.d("rain", "count  "+String.valueOf(count));
        //for (int i=0;i<count;i++) {
            NumberRainItem numberRainItem =new NumberRainItem(getContext());
            numberRainItem.setHightLightColor(hightLightColor);
            numberRainItem.setTextSize(textSize);
            numberRainItem.setNormalColor(normalColor);
            LayoutParams layoutParams =new LayoutParams((int) (textSize), getMeasuredHeight());
            layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
            numberRainItem.setStartOffset((long) (Math.random() * 1000));
            addView(numberRainItem, layoutParams);

       // }
    }
}

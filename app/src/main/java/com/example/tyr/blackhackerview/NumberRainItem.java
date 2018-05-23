package com.example.tyr.blackhackerview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tyr on 2018/5/21.
 */

public class NumberRainItem extends View{

    private Paint paint;
    float textSize = 15* getResources().getDisplayMetrics().density;
    int normalColor = Color.GREEN;
    int hightLightColor = Color.YELLOW;
    private float nowHeight = 0f;
    private int hightLightNumIndex = 0;
    private long startOffset  = 0L;
    private Context context;
    private float height;

    public float getTextSize() {
        return textSize;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setTextSize(float textSize) {
        this.textSize = textSize;
        if (isAttachedToWindow()) {
            postInvalidate();
        }
    }

    public int getNormalColor() {
        return normalColor;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setNormalColor(int normalColor) {
        this.normalColor = normalColor;
        if (isAttachedToWindow()) {
            postInvalidate();
        }
    }

    public int getHightLightColor() {
        return hightLightColor;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setHightLightColor(int hightLightColor) {
        this.hightLightColor = hightLightColor;
        if (isAttachedToWindow()) {
            postInvalidate();
        }
    }

    public float getNowHeight() {
        return nowHeight;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setNowHeight(float nowHeight) {
        this.nowHeight = nowHeight;
        if (isAttachedToWindow()) {
            postInvalidate();
        }
    }

    public int getHightLightNumIndex() {
        return hightLightNumIndex;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setHightLightNumIndex(int hightLightNumIndex) {
        this.hightLightNumIndex = hightLightNumIndex;
        if (isAttachedToWindow()) {
            postInvalidate();
        }
    }

    public long getStartOffset() {
        return startOffset;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setStartOffset(long startOffset) {
        this.startOffset = startOffset;
        if (isAttachedToWindow()) {
            postInvalidate();
        }
    }

    public NumberRainItem(Context context) {
        this(context,null);
    }

    public NumberRainItem(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NumberRainItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context  = context;
        if (attrs!=null){
            parseAttr(attrs);
        }
        initPaint();

    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    private void parseAttr(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NumberRainItem);
        normalColor = typedArray.getColor(R.styleable.NumberRainItem_normalColor, Color.GREEN);
        hightLightColor = typedArray.getColor(R.styleable.NumberRainItem_highLightColor, Color.RED);
        startOffset = typedArray.getInt(R.styleable.NumberRainItem_startOffset, 0);
        textSize = typedArray.getDimension(R.styleable.NumberRainItem_textSize, textSize);
        typedArray.recycle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.height = getHeight();
        configPaint();
        if (isShowAllNumber()){
            drawTotalNumbers(canvas);
        }else {
            drawPartNumbers(canvas);
        }
    }

    private void drawPartNumbers(Canvas canvas) {
        int count = (int) (nowHeight / textSize);
        nowHeight += textSize;
        drawNumbers(canvas, count);
    }

    private void drawTotalNumbers(Canvas canvas) {
        int count = (int) (height / textSize);
        drawNumbers(canvas, count);
    }

    private void drawNumbers(Canvas canvas, int count) {
        if (count == 0) {
            postInvalidateDelayed(100L);
        } else {
            float offset = 0f;
            for (int i=0;i<count;i++) {
                String text = String.valueOf((Math.random() * 9));
                if (hightLightNumIndex == i) {
                    paint.setColor(hightLightColor);
                    paint.setShadowLayer(10f, 0f, 0f, hightLightColor);
                } else {
                    paint.setColor(normalColor);
                    paint.setShadowLayer(10f, 0f, 0f, normalColor);
                }
                canvas.drawText(text, 0f, textSize + offset, paint);
                offset += textSize;
            }
            if (!isShowAllNumber()) {
                hightLightNumIndex++;
            } else {
                hightLightNumIndex = (++hightLightNumIndex) % count;
            }
            postInvalidateDelayed(100L);


        }
    }

    private void configPaint() {
        paint.setTextSize(textSize);
        paint.setColor(normalColor);
    }

    private boolean isShowAllNumber(){
        return nowHeight >= height;
    }
}

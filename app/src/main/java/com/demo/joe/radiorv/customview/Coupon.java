package com.demo.joe.radiorv.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.demo.joe.radiorv.R;

/**
 * Created by weizijie on 2018/7/16.
 */

public class Coupon extends android.support.v7.widget.AppCompatTextView {

    private int boderColor;
    private String boderType;
    private Paint mPaint;

    public Coupon(Context context) {
        this(context,null);
    }

    public Coupon(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Coupon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取属性
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs,R.styleable.BorderTextView,defStyleAttr,0);
        int n = ta.getIndexCount();
        for(int i = 0; i < n ;i++){
            int attr = ta.getIndex(i);
            switch (attr){
                case R.styleable.BorderTextView_borderColor:
                    boderColor = ta.getColor(attr, Color.BLACK);
                    break;
            }
        }
        ta.recycle();
        mPaint = new Paint();
        mPaint.setColor(boderColor);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        Paint p = getPaint();
        float size = p.getTextSize();
        p.setTextSize(size / 2);
        int x,y;
        Rect bound = new Rect();
        p.getTextBounds(getText().toString(),0,getText().toString().length(),bound);
        x = getMeasuredWidth() / 2 - bound.width() / 2;
        y = getMeasuredHeight() / 2 + bound.height() / 2;

        canvas.drawPath(getBorderPath(), mPaint);
        canvas.save();

        canvas.drawText(getText().toString(), x, y, p);
        canvas.restore();
    }

    private Path getBorderPath() {

        Path path = new Path();
        RectF oval = new RectF(-25,25,25,75);
        RectF oval1 = new RectF(200,25,250,75);
        path.lineTo(0,0);
        path.lineTo(0,25);
        path.addArc(oval,270,180);
        path.lineTo(0,100);
        path.lineTo(225,100);
        path.lineTo(225,75);
        path.addArc(oval1,90,180);
        path.lineTo(225,0);
        path.lineTo(0,0);
        return path;

    }
}

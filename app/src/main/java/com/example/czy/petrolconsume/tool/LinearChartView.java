package com.example.czy.petrolconsume.tool;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dllo on 17/4/19.
 */

public class LinearChartView extends View{
    private int width;
    private int height;
    private static final int PADDING_WIDTH = 100;
    private static final int PADDING_HEIGHT = 100;
    private static final int COUNT_X_MARK = 35;
    private static final int COUNT_Y_MARK = 15;
    private Paint paint,paintM;
    private String[] consumption = new String[]{"15","14","13","12","11","10","9","8","7","6","5","4","3","2","1","0"};
    public LinearChartView(Context context) {
        this(context,null);
    }

    public LinearChartView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LinearChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintM = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(2);
        paintM.setStrokeWidth(0.5f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawAxisX(canvas);
        drawAxisY(canvas);
        drawMarkX(canvas);
        drawMarkY(canvas);
    }

    private void drawAxisX(Canvas canvas){
        float startX = PADDING_WIDTH;
        float startY = height - PADDING_HEIGHT;
        float stopX = width - PADDING_WIDTH;
        float stopY = height - PADDING_HEIGHT;
        paint.setColor(0xffffffff);
        canvas.drawLine(startX,startY,stopX,stopY,paint);
        float length = (width - PADDING_WIDTH * 2) / COUNT_X_MARK;
        for (int i = 0; i < COUNT_X_MARK; i++) {
            float startXM = PADDING_WIDTH + length * (i + 1);
            float startYM = PADDING_HEIGHT;
            float stopXM = PADDING_WIDTH + length * (i + 1);
            float stopYM = height - PADDING_HEIGHT;
            paintM.setColor(0xffffffff);
            canvas.drawLine(startXM,startYM,stopXM,stopYM,paintM);
        }
    }
    private void drawAxisY(Canvas canvas){
        float startX = PADDING_WIDTH;
        float startY = PADDING_HEIGHT;
        float stopX = PADDING_WIDTH;
        float stopY = height - PADDING_HEIGHT;
        paint.setColor(0xffffffff);
        canvas.drawLine(startX,startY,stopX,stopY,paint);
        float length = (height - PADDING_WIDTH * 2) / COUNT_Y_MARK;
        for (int i = 0; i < COUNT_Y_MARK; i++) {
            float startXM = PADDING_WIDTH;
            float startYM = PADDING_HEIGHT + length * (i + 1);
            float stopXM = width - PADDING_WIDTH;
            float stopYM = PADDING_HEIGHT + length * (i + 1);
            paintM.setColor(0xffffffff);
            canvas.drawLine(startXM,startYM,stopXM,stopYM,paintM);
        }
    }
    private void drawMarkX(Canvas canvas){
        float length = (width - PADDING_WIDTH * 2) / (COUNT_X_MARK);
        for (int i = 0; i < COUNT_X_MARK; i++) {
            float startX = PADDING_WIDTH + length * (i + 1);
            float startY = height - PADDING_HEIGHT - 20;
            float stopY = height - PADDING_HEIGHT;
            paint.setColor(0xffffffff);
            canvas.drawLine(startX,startY,startX,stopY,paint);
        }
        float startFiveX = PADDING_WIDTH + length * 7;
        float startSixX = PADDING_WIDTH + length * 19;
        float startSevenX = PADDING_WIDTH + length * 31;
        float startFiveY = (height - PADDING_HEIGHT) + 50;
        float startX = width - PADDING_WIDTH + 10;
        float startY = height - PADDING_HEIGHT + 10;
        paint.setTextSize(30);
        canvas.drawText("2015",startFiveX,startFiveY,paint);
        canvas.drawText("2016",startSixX,startFiveY,paint);
        canvas.drawText("2017",startSevenX,startFiveY,paint);
        canvas.drawText("月份",startX,startY,paint);
    }
    private void drawMarkY(Canvas canvas){
        float length = (height - PADDING_WIDTH * 2) / (COUNT_Y_MARK);
        for (int i = 0; i < COUNT_Y_MARK; i++) {
            float startX = PADDING_WIDTH;
            float stopX = PADDING_WIDTH + 20;
            float startY = PADDING_HEIGHT + length * (i);
            paint.setColor(0xffffffff);
            canvas.drawLine(startX,startY,stopX,startY,paint);
        }
        for (int i = 0; i <= COUNT_Y_MARK; i++) {
            float x = 50;
            float y = PADDING_HEIGHT + length * (i) + 10;
            paint.setTextSize(30);
            canvas.drawText(consumption[i],x,y,paint);
        }
        float startY = PADDING_HEIGHT - 30;
        float startX = 60;
        paint.setTextSize(30);
        canvas.drawText("油耗",startX,startY,paint);
    }
    }

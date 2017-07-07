package com.xinhe.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jinfan on 17/7/7.
 */

public class SeventView extends View {
    Paint p;
    int jibianxing=7;

    public SeventView(Context context) {
        super(context);
        initView();
    }

    public SeventView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SeventView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        p=new Paint();
        p.setColor(Color.RED);
        p.setStrokeWidth(5);
        p.setStyle(Paint.Style.FILL);
        p.setAntiAlias(true);
        p.setDither(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x=getMeasuredWidth();
        int y=getMeasuredHeight();

        canvas.drawPoint(x/2,y/2,p);
        Point first=new Point(x/2+200,y/2);
        int jiaodu =360/jibianxing;

        for(int i=0;i<jibianxing;i++){

            Point rotePoint = JiaoduUtil.getRotePoint(first, new Point(x / 2, y / 2), (i + 1) * jiaodu);

            canvas.drawPoint(rotePoint.x,rotePoint.y,p);
        }


    }
}

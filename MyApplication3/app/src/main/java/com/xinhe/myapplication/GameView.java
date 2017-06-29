package com.xinhe.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinfan on 17/6/28.
 */

public class GameView extends View {

    Paint p;

    Paint p_point ;


    Paint p_power ;

    double sin60=Math.sin(360/6 * Math.PI / 180);

    double sin45=Math.sin(45 * Math.PI / 180);

    double cos60=Math.cos(360/6 * Math.PI / 180);

    int pointR=20;

    float maxPower=100;

    float[] power;
    List<Point> powerlist;


    public GameView(Context context) {
        super(context);
        initView();
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    public void setPower(float[] power) {
        this.power = power;
    }

    private void initView() {

        powerlist=new ArrayList<>();

        power=new float[]{60,80,90,40,30,70};

        p=new Paint();
        p.setAntiAlias(true);
        p.setDither(true);
        p.setStrokeWidth(5);
        p.setColor(Color.BLUE);
        p.setStyle(Paint.Style.STROKE);


        p_point=new Paint();
        p_point.setAntiAlias(true);
        p_point.setDither(true);
        p_point.setStrokeWidth(5);
        p_point.setStyle(Paint.Style.FILL);
        p_point.setColor(Color.RED);





    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x=getMeasuredWidth();

        int y=getMeasuredHeight();
        for(int i=0;i<5;i++) {
            drawSixView(i==4,canvas, new Point(x / 2, y / 2), Color.BLUE, 50*(i+1));

        }




    }



    public void drawSixView(boolean showPoint,Canvas canvas,Point center,int color,float lenght){
        Path path=new Path();
        int x=center.x;
        int y=center.y;

        if(showPoint)
        drawPoint(x,y,canvas);

        //一号点
        path.moveTo(x+lenght,y);
        if(showPoint) {
            drawPoint(x + lenght, y, canvas);
            float temp=power[0]/maxPower;
            powerlist.add(new Point((int)(lenght*temp),0));

        }




        //二号点
        path.lineTo(x+lenght/2,(float) (y+sin60*lenght));

        p_point.setColor(Color.GREEN);
        if(showPoint) {
            drawPoint(x + lenght / 2, (float) (y + sin60 * lenght), canvas);

            float temp=power[1]/maxPower;

            powerlist.add(new Point((int)(lenght / 2*temp),(int)(sin60 * lenght*temp)));

        }





        //三号点
        path.lineTo(x-lenght/2,(float) (y+sin60*lenght));

        p_point.setColor(Color.YELLOW);
        if(showPoint) {

            drawPoint(x - lenght / 2, (float) (y + sin60 * lenght), canvas);

            float temp=power[2]/maxPower;

            powerlist.add(new Point((int)(lenght / (-2)*temp),(int)(sin60 * lenght*temp)));

        }

        //四号点
        path.lineTo(x-lenght, y);
        if(showPoint)
        drawPoint(x-lenght, y,canvas);




        //五号点
        path.lineTo(x-lenght/2,(float) (y-sin60*lenght));
        if(showPoint)
        drawPoint(x-lenght/2,(float) (y-sin60*lenght),canvas);



        //六号点
        p_point.setColor(Color.BLACK);

        path.lineTo(x+lenght/2,(float) (y-sin60*lenght));
        if(showPoint)
        drawPoint(x+lenght/2,(float) (y-sin60*lenght),canvas);


       //闭合
        path.lineTo(x+lenght,y);

        canvas.drawPath(path,p);






    }

    public void drawPoint(float x,float y,Canvas canvas){
        RectF rectF=new RectF((float) (x-sin45*pointR),(float) (y-sin45*pointR),(float) (x+sin45*pointR),(float) (y+sin45*pointR));

        canvas.drawOval(rectF,p_point);


    }



}

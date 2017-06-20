package com.xinhe.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinfan on 17/6/16.
 */

public class PasswordView extends View {

    private Paint paint;


    private Paint paintBlue;

    private Paint paintLine;

    private int line_space=250;

    private  int r=25;

    private  int isNear=50;

    private boolean isDown;

    private boolean isMove;

    private boolean isUp;

    private float down_x;
    private float down_y;

    private  int nearPoint;

    private MyPassWordListen listen;


    private float current_x;
    private float current_y;

    private List<Integer> listpostion=new ArrayList<>();

    private List<Point> list=new ArrayList<>();

    public PasswordView(Context context) {
        super(context);
        initView();
    }

    public PasswordView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    public PasswordView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void setListen(MyPassWordListen listen) {
        this.listen = listen;
    }



    private void initView() {

        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);


        paintBlue=new Paint();
        paintBlue.setAntiAlias(true);
        paintBlue.setColor(Color.RED);
        paintBlue.setStyle(Paint.Style.FILL);

        paintLine=new Paint();
        paintLine.setAntiAlias(true);
        paintLine.setColor(Color.BLUE);
        paintLine.setStyle(Paint.Style.FILL);
        paintLine.setStrokeWidth(10);

        for(int i=0;i<3;i++){

            for(int j=0;j<3;j++){
                list.add(new Point(line_space*j,line_space*i));
            }

        }



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.i("onDraw","1");
        for(int i=0;i<list.size();i++){
            RectF rectF=new RectF(list.get(i).x*1.0f,list.get(i).y*1.0f,list.get(i).x*1.0f+r,list.get(i).y*1.0f+r);
            canvas.drawOval(rectF,paint);

        }


        if(isMove&&listpostion.size()<9){


            if(listpostion.size()>1){

                for(int i=0;i<listpostion.size()-1;i++){
                    int c=listpostion.get(i);
                    int n=listpostion.get(i+1);
                    Log.i("onDraw1",c+"ss"+n);

                    canvas.drawLine(list.get(c).x,list.get(c).y,list.get(n).x,list.get(n).y,paintLine);

                    Log.i("onDraw1",list.get(c).x+"ss"+list.get(c).y+"ss"+list.get(n).x+"ss"+list.get(n).y);

                }
                 if(!isUp) {
                     canvas.drawLine(list.get(nearPoint).x, list.get(nearPoint).y, current_x, current_y, paintLine);
                 }

            }else if(listpostion.size()>0){

                if(!isUp) {
                    canvas.drawLine(list.get(nearPoint).x, list.get(nearPoint).y, current_x, current_y, paintLine);
                }

            }


        }else {

            Log.i("onDraw","3");
        }




    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();


        current_x = event.getX();
        current_y = event.getY();


        if(action==MotionEvent.ACTION_DOWN){
            if(isNearAnyPoint()) {

                Log.i("isNearAnyPoint","ok");
                isDown = true;
                down_x = current_x;
                down_y = current_y;
                if(listen!=null) {
                    listen.passwordInput(nearPoint+1);
                }
            }else {
                Log.i("isNearAnyPoint","not");
            }


        }else if(action==MotionEvent.ACTION_MOVE){

            if(isDown&&listpostion.size()<9){
                isMove=true;
                isNearAnyPoint();
                invalidate();
            }

        }else if(action==MotionEvent.ACTION_UP){
            isDown=false;
            isDown=false;
            String temp="";
            for(int i=0;i<listpostion.size();i++){
                temp=temp+(listpostion.get(i)+1);
            }

            if(listen!=null) {
                listen.passwordInputEnd(temp);
            }

            listpostion.clear();
            invalidate();

        }

        return true;

    }


    public boolean isNearAnyPoint(){

        Point current=new Point((int)current_x,(int)current_y);
        int tempx= (int)current_x/line_space; //第几行
        int tempy= (int)current_y/line_space; //第几列


        Log.i("isNearAnyPointx",tempx+"");
        Log.i("isNearAnyPointy",tempy+"");


        if(tempx==0&&tempy==0){

            return  isNear(current,0)||isNear(current,1)||isNear(current,3)||isNear(current,4);
        }else if(tempx==1&&tempy==1){

            return  isNear(current,4)||isNear(current,5)||isNear(current,7)||isNear(current,8);
        }else if(tempx==1&&tempy==0){

            return  isNear(current,1)||isNear(current,2)||isNear(current,4)||isNear(current,5);
        } else if(tempx==0&&tempy==1){

            return  isNear(current,3)||isNear(current,4)||isNear(current,6)||isNear(current,7);
        }else {

            return  false;

        }




    }



    public boolean isNear(Point p1,int postion){

        if(listpostion.toString().contains(postion+"")){


            return  false;
        }

        Point p2=new Point(list.get(postion).x,list.get(postion).y);

        int i = (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
        if(i<isNear*isNear){


            listpostion.add(postion);
            Log.i("isNearAnyPointss",listpostion.toString()+"");
            invalidate();
            nearPoint=postion;
            return true;

        }else {
            Log.i("isNearAnyPointx",p1.x+"ss"+p2.x);

            Log.i("isNearAnyPointy",p1.y+"ss"+p2.y);

            Log.i("isNearAnyPoint",i+"dayu"+isNear*isNear);
            return false;
        }



    }


    public interface  MyPassWordListen{

        public void passwordInput(int postion);

        public void passwordInputEnd(String postion);



    }


}

package com.xinhe.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by jinfan on 17/10/23.
 */

public class Guanji extends View {
    int view_w,view_h;
    Paint p1,p2;
    int small_w;
    float small_x,small_y;
    float current_x,first_x;
    int current_zt;//记录当前状态，0是没有触动or up，1是down，2是move，
    float end;





    public Guanji(Context context) {
        super(context);
        initView();
    }

    public Guanji(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }



    public Guanji(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    private void initView() {
        p1=new Paint();
        p1.setColor(Color.RED);
        p1.setAntiAlias(true);
        p1.setStyle(Paint.Style.FILL);
        p1.setDither(true);

        p2=new Paint();
        p2.setColor(Color.BLUE);
        p2.setAntiAlias(true);
        p2.setStyle(Paint.Style.FILL);
        p2.setDither(true);
        small_x=10;
        small_y=10;





    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        view_w=getMeasuredWidth();
        view_h=getMeasuredHeight();
        small_w=view_h;
        Log.i("view",view_w+""+view_h);
        end=view_w-small_w-10;

    }

    //p1是下面的，p2是小滑快
    @Override
    protected void onDraw(Canvas canvas) {

        RectF r1=new RectF(small_x,small_y,view_w,view_h);
        canvas.drawRect(r1,p1);

        RectF  r2=new RectF(small_x,small_y,small_x+small_w,10+small_w);
        canvas.drawRect(r2,p2);

    }
  //  current_zt;//记录当前状态，0是没有触动or up，1是down，2是move，
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();


        current_x = event.getX();



        if(action==MotionEvent.ACTION_DOWN){
            if(current_zt==0) {
                Log.i("onTouchEvent","down");
                current_zt=1;
                first_x = event.getX();



            }else {
                Log.i("onTouchEvent","down is wuxiao");
            }


        }else if(action==MotionEvent.ACTION_MOVE){
            if(current_zt==1){
                current_zt=2;
                Log.i("onTouchEvent","first move");
            }

            if(current_zt==2){

                if(current_x>first_x){
                    small_x=current_x-first_x;
                    Log.i("onTouchEvent"," move"+small_x);
                    if(small_x<end+10){
                        invalidate();
                    }

                }
            }



        }else if(action==MotionEvent.ACTION_UP){
            Log.i("onTouchEvent","up");
            if(small_x>end){
                Toast.makeText(getContext(),"ok",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getContext(),"not ok",Toast.LENGTH_LONG).show();
            }
            current_zt=0;
            current_x=0;
            first_x=0;
            small_x=10;
            small_y=10;
            invalidate();

        }

        return true;

    }

}

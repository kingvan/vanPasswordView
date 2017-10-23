package com.xinhe.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jinfan on 17/7/11.
 */

public class BarCharView extends View {
    private Paint paint_line;   //外框条的paint
    private int color_line= Color.GRAY;   //外框条的paint color

    private  int origin_x=0;
    private  int origin_y=0;

    private  int end_x=0;
    private  int end_y=0;

    private  int commerPing=10;




    public BarCharView(Context context) {
        super(context);
        initView();
    }

    public BarCharView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }



    public BarCharView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        paint_line=new Paint();
        paint_line.setAntiAlias(true);
        paint_line.setColor(color_line);
        paint_line.setStyle(Paint.Style.STROKE);
    }


    private  void  getOriginXAndY(){
        origin_x=commerPing;
        origin_y=commerPing;
        end_x=getMeasuredWidth()-commerPing;
        end_y=getMeasuredHeight()-commerPing;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getOriginXAndY();
        drawOntLine(canvas);


    }

    private void drawOntLine(Canvas canvas) {

        canvas.drawLine(origin_x,origin_y,origin_x,end_y ,paint_line);
        canvas.drawLine(origin_x,end_y,end_x,end_y ,paint_line);

//        Bitmap temp= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
//        RectF rectF = new RectF(0, 0, getWidth() / 2, getHeight() / 2);
//        Rect rectc = new Rect(0, 0, temp.getWidth() / 2, temp.getHeight() / 2);
//        canvas.drawColor(Color.RED);
//        canvas.drawBitmap(temp,rectc,rectF,paint_line);


    }
}

package com.xinhe.myapplication;

import android.graphics.Point;

/**
 * Created by jinfan on 17/7/7.
 */

public class JiaoduUtil {


    public static Point getRotePoint(Point scr,Point des,int jiaodu){

        double sinx=Math.sin(jiaodu * Math.PI / 180);

        double cosx=Math.cos(jiaodu * Math.PI / 180);

        double x0= (scr.x - des.x)*cosx - (scr.y - des.y)*sinx + des.x ;

        double y0= (scr.x - des.x)*sinx + (scr.y - des.y)*cosx + des.y ;

        return  new Point((int)x0,(int)y0);

    }
}

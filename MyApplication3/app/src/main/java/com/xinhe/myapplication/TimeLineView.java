package com.xinhe.myapplication;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jinfan on 17/6/20.
 */

//仿群里一个同学的控件，原地址：https://github.com/vivian8725118/TimeLine


public class TimeLineView extends RecyclerView{





    public TimeLineView(Context context) {
        super(context);
        initView();

    }

    public TimeLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TimeLineView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {

        setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        setAdapter(new MyAdpater());
        addItemDecoration(new MyItemDecoration());



    }

    class MyAdpater extends RecyclerView.Adapter<MyViewHodle>{


        @Override
        public MyViewHodle onCreateViewHolder(ViewGroup parent, int viewType) {


            return new MyViewHodle(LayoutInflater.from(getContext()).inflate(R.layout.item_timeline,null,false));
        }

        @Override
        public void onBindViewHolder(MyViewHodle holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 5;
        }
    }


    class MyViewHodle extends RecyclerView.ViewHolder{
        TextView tv;


        public MyViewHodle(View itemView) {
            super(itemView);
            tv= (TextView) itemView.findViewById(R.id.text);
        }
    }


    class MyItemDecoration extends ItemDecoration{
        Paint paint=new Paint();

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            super.getItemOffsets(outRect, view, parent, state);
            if(parent.getChildAdapterPosition(view)==0){
                outRect.top=0;
            }else {
                outRect.top=70;
            }


            if(parent.getChildAdapterPosition(view)%2==0){
                outRect.left=50;
                outRect.right=20;
            }else {
                outRect.left=20;
                outRect.right=50;
            }

        }


        @Override
        public void onDraw(Canvas c, RecyclerView parent, State state) {
            super.onDraw(c, parent, state);
            View childAt = parent.getChildAt(0);
            paint.setColor(Color.RED);
            paint.setStrokeWidth(8);
            int  h=childAt.getHeight();
            int  juzhong=childAt.getRight()+20;
            int  t=childAt.getTop()-20;
            c.drawLine(juzhong,t,juzhong,t+3*(h+70)+20,paint);
            paint.setColor(Color.BLUE);
            for (int i=0;i<parent.getChildCount();i++){
                View childAt1 = parent.getChildAt(i);
                int y=0;

                y=childAt1.getTop()+20;

                c.drawOval(new RectF(juzhong-15,y-15,juzhong+15,y+15),paint);



            }



        }
    }

}

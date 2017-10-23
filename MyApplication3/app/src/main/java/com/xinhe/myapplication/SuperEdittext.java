package com.xinhe.myapplication;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinfan on 17/7/27.
 */

public class SuperEdittext extends android.support.v7.widget.AppCompatEditText {

    List<atHodle> list_at;

    public SuperEdittext(Context context) {
        super(context);
    }

    public SuperEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }



    public SuperEdittext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initView() {
        list_at=new ArrayList<>();



        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("onTextChanged222",s+"ss"+start+"ss"+before+"ss"+count);
                String temp=s.toString().substring(start,start+count);
                Log.i("onTextChanged2221",temp);

                if(count>1&&temp.length()>1&&temp.contains("@")){

                    Log.i("Changed222新加入一个",temp+"");
                    list_at.add(new atHodle(start,temp));
                }



            }

            @Override
            public void afterTextChanged(Editable s) {

                Log.i("afterTextChanged222",s.toString());

            }
        });
    }

    class atHodle{
        private  int begin;
        private  int size;
        private  int end;
        private  String orgin;

        public atHodle(int begin, String orgin) {
            this.begin = begin;
            this.orgin = orgin;
            this.size=orgin.length();
            this.end=begin+size;
        }
    }
}

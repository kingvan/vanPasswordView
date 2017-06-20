package com.xinhe.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;


import static android.provider.Telephony.Mms.Part.TEXT;

public class MainActivity extends Activity {


    PasswordView password;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        password=(PasswordView) findViewById(R.id.password);
        password.setListen(new PasswordView.MyPassWordListen() {
            @Override
            public void passwordInput(int postion) {
                Log.i("passwordInput1",postion+"");

            }

            @Override
            public void passwordInputEnd(String postion) {
                Log.i("passwordInput2",postion+"");
            }
        });
    }
}

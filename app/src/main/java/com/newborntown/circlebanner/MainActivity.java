package com.newborntown.circlebanner;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SoloViewPager mViewPager;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mViewPager.toNext();
            handler.sendEmptyMessageDelayed(1, 1500);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (SoloViewPager) findViewById(R.id.vp_content);
        mViewPager.setAdapter(new ItemViewAdapter());
        handler.sendEmptyMessageDelayed(1, 1000);
    }
}

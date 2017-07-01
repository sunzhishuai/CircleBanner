package com.newborntown.circlebanner;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by sunzhishuai on 17/6/22.
 * E-mail itzhishuaisun@sina.com
 */

public class PageTransFormer implements ViewPager.PageTransformer {
    private float scaleFall = 0.5f; //缩放比
    private float heightFall = -200.0f; //落差
    private float translationX = 0;//缩放引起误差

    @Override
    public void transformPage(View page, float position) {
        if (position < -1) {
            page.setScaleY(scaleFall);
            page.setScaleX(scaleFall);
            page.setTranslationY(heightFall);
            page.setTranslationX(-translationX);
        } else if (position < 0) {
            float percent = 1 - scaleFall;
            float scale = 1 - percent * Math.abs(position);
            page.setTranslationY(-heightFall * position);
            page.setScaleY(scale);
            page.setScaleX(scale);
            page.setTranslationX(position * translationX);
        } else if (position < 1) {
            float percent = 1 - scaleFall;
            float v = scaleFall + percent * (1 - position);
            page.setScaleY(v);
            page.setScaleX(v);
            page.setTranslationY(position * heightFall);
            page.setTranslationX(position * translationX);
        } else {
            page.setScaleY(scaleFall);
            page.setScaleX(scaleFall);
            page.setTranslationY(heightFall);
            page.setTranslationX(translationX);
        }
    }

    public float getScaleFall() {
        return scaleFall;
    }

    public void setScaleFall(float scaleFall) {
        this.scaleFall = scaleFall;
    }

    public float getHeightFall() {
        return heightFall;
    }

    public void setHeightFall(float heightFall) {
        this.heightFall = heightFall;
    }

    public float getTranslationX() {
        return translationX;
    }

    public void setTranslationX(float translationX) {
        this.translationX = translationX*scaleFall/2;
    }
}

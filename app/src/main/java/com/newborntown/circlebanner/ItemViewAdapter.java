package com.newborntown.circlebanner;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by sunzhishuai on 17/6/30.
 * E-mail itzhishuaisun@sina.com
 */

public class ItemViewAdapter extends PagerAdapter {
    @Override
    public int getCount() {
        return 30;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View inflate = View.inflate(container.getContext(), R.layout.item_layout, null);
        ImageView viewById = (ImageView) inflate.findViewById(R.id.iv_center);

        if (position % 2 == 0) {
            viewById.setBackgroundResource(R.mipmap.belle6);
        } else {
            viewById.setBackgroundResource(R.mipmap.belle9);

        }
        container.addView(inflate);
        return inflate;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}

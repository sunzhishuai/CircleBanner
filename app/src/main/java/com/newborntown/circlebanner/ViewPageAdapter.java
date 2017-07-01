package com.newborntown.circlebanner;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by sunzhishuai on 17/6/30.
 * E-mail itzhishuaisun@sina.com
 */

public class ViewPageAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;

    public ViewPageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


}

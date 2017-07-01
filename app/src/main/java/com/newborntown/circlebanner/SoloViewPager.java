package com.newborntown.circlebanner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * Created by sunzhishuai on 17/6/30.
 * E-mail itzhishuaisun@sina.com
 */

public class SoloViewPager extends FrameLayout {
    private int itemWidth = 360;
    private ViewPager viewPager;
    private Paint paint;
    private int measuredHeight;
    private int measuredWidth;
    private PageTransFormer pageTransFormer;
    private ViewPagerScroller mViewPagerScroller;//控制ViewPager滑动速度的Scroller

    public SoloViewPager(Context context) {
        this(context, null);
    }

    public SoloViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SoloViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        viewPager = new ViewPager(context);
        LayoutParams layoutParams = new LayoutParams(itemWidth, ViewPager.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER | Gravity.BOTTOM;
        viewPager.setLayoutParams(layoutParams);
        pageTransFormer = new PageTransFormer();
        viewPager.setPageTransformer(false, pageTransFormer);
        viewPager.setOffscreenPageLimit(4);
        addView(viewPager);
        //绘制初始化
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        // 初始化Scroller
        initViewPagerScroll();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measuredHeight = getMeasuredHeight();
        measuredWidth = getMeasuredWidth();
        int measuredWidth = viewPager.getMeasuredWidth();
        pageTransFormer.setTranslationX(measuredWidth);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Path path = getPath();
        canvas.drawPath(path, paint);
        super.dispatchDraw(canvas);
    }

    public void setAdapter(PagerAdapter adapter) {
        viewPager.setAdapter(adapter);
    }

    public Path getPath() {
        Path path = new Path();
        float heightFall = pageTransFormer.getHeightFall();
        float translationX = pageTransFormer.getTranslationX();
        float pathStartY = heightFall + 0.5f * measuredHeight;
        path.moveTo(-translationX, pathStartY);
        path.quadTo(measuredWidth / 2, measuredHeight, measuredWidth + translationX, pathStartY);
        return path;
    }

    /**
     * 设置ViewPager的滑动速度
     */
    private void initViewPagerScroll() {
        try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            mViewPagerScroller = new ViewPagerScroller(
                    viewPager.getContext());
            mScroller.set(viewPager, mViewPagerScroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Scroller 来控制切换的速度
     */
    public static class ViewPagerScroller extends Scroller {
        private int mDuration = 3000;// ViewPager默认的最大Duration 为600,我们默认稍微大一点。值越大越慢。
        private boolean mIsUseDefaultDuration = false;

        public ViewPagerScroller(Context context) {
            super(context);
        }

        public ViewPagerScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public ViewPagerScroller(Context context, Interpolator interpolator, boolean flywheel) {
            super(context, interpolator, flywheel);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, mIsUseDefaultDuration ? duration : mDuration);
        }

        public void setUseDefaultDuration(boolean useDefaultDuration) {
            mIsUseDefaultDuration = useDefaultDuration;
        }

        public boolean isUseDefaultDuration() {
            return mIsUseDefaultDuration;
        }

        public void setDuration(int duration) {
            mDuration = duration;
        }


        public int getScrollDuration() {
            return mDuration;
        }
    }

    /**
     * 切换到下一个
     */
    public void toNext() {
        if (viewPager.getCurrentItem() < viewPager.getAdapter().getCount()-1) {
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
        }
    }
}

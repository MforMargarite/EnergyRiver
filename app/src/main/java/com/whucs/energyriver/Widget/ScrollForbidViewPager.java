package com.whucs.energyriver.Widget;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

//去除滑动操作和效果的ViewPager
public class ScrollForbidViewPager extends ViewPager{
    public ScrollForbidViewPager(Context context) {
        super(context);
        initViewPagerScroll(context);
    }

    public ScrollForbidViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewPagerScroll(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    private void initViewPagerScroll(Context context) {
        try {
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            mScroller.set(this, new MyScroller(context));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    class MyScroller extends Scroller {
        private int mScrollDuration = 0;

        public MyScroller(Context context) {
            super(context);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, mScrollDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy, mScrollDuration);
        }
    }
}

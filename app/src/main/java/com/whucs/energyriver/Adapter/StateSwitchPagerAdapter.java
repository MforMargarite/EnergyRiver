package com.whucs.energyriver.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.R;


public class StateSwitchPagerAdapter extends PagerAdapter {
    private View[] views;

    public StateSwitchPagerAdapter(Context context,View view) {
        ViewPager.LayoutParams layoutParams = new ViewPager.LayoutParams();
        views = new View[getCount()];
        views[Common.LOADING] = LayoutInflater.from(context).inflate(R.layout.loading, null);
        views[Common.CONTENT] = view;
        views[Common.ERROR] = LayoutInflater.from(context).inflate(R.layout.error, null);
        for (int i=0;i<views.length;i++) {
            views[i].setLayoutParams(layoutParams);
            views[i].setMinimumWidth(ViewPager.LayoutParams.MATCH_PARENT);
            views[i].setMinimumHeight(ViewPager.LayoutParams.MATCH_PARENT);
        }
    }

    public void setContentLayout(View view){
        views[Common.CONTENT] = view;
    }

    public void setErrorLayout(View view){
        views[Common.ERROR] = view;
    }

    public void setLoadingLayout(View view){
        views[Common.LOADING] = view;
    }

    public View getViewByType(int type){
        return views[type];
    }

    @Override
    public int getCount() {
        return 3;//页面加载的三种状态：加载中、加载完成和加载失败
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views[position]);
        return views[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}

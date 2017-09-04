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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


public class StateSwitchPagerAdapter extends PagerAdapter {
    //private View[] views;
    private LinkedHashMap<String,View> mapView;
    public StateSwitchPagerAdapter(Context context,View content) {
        ViewPager.LayoutParams layoutParams = new ViewPager.LayoutParams();
        mapView = new LinkedHashMap<>();
        mapView.put("loading",LayoutInflater.from(context).inflate(R.layout.loading, null));
        mapView.put("content",content);
        mapView.put("error",LayoutInflater.from(context).inflate(R.layout.error, null));
        Iterator<Map.Entry<String,View>>iterator = mapView.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,View>entry = iterator.next();
            View view = entry.getValue();
            view.setLayoutParams(layoutParams);
            view.setMinimumWidth(ViewPager.LayoutParams.MATCH_PARENT);
            view.setMinimumHeight(ViewPager.LayoutParams.MATCH_PARENT);
            mapView.put(entry.getKey(),view);
        }
    }

    public void setViewWithTag(String tag,View view){
        mapView.put(tag,view);
    }

    public View getViewByTag(String tag){
        return mapView.get(tag);
    }

    public int getIndexByTag(String tag){return new ArrayList<>(mapView.keySet()).indexOf(tag);}

    public void addState(String tag,View view){
        mapView.put(tag,view);
    }

    @Override
    public int getCount() {
        return mapView.size();//页面加载的三种状态：加载中、加载完成和加载失败
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView((View)mapView.values().toArray()[position]);
        return mapView.values().toArray()[position];
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

package com.whucs.energyriver.Widget;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.whucs.energyriver.Adapter.StateSwitchPagerAdapter;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.R;

public class StateSwitchFragment extends Fragment {
    ScrollForbidViewPager viewPager;
    StateSwitchPagerAdapter adapter;
    Activity activity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.state_switch,null);
        viewPager = (ScrollForbidViewPager) view.findViewById(R.id.container);
        return view;
    }

    public void iniAdapter(View view){
        if(activity == null)
            activity  = getActivity();
        adapter = new StateSwitchPagerAdapter(activity,view);//加载内容区至ViewPager中
        adapter.setViewWithTag("error",initErrorWidget(adapter.getViewByTag("error")));
        viewPager.setAdapter(adapter);
    }

    public void addState(String tag,View view){
        adapter.addState(tag,view);
        adapter.notifyDataSetChanged();
    }


    private View initErrorWidget(View view){
        TextView reload = (TextView) view.findViewById(R.id.reload);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reload();
            }
        });
        return view;
    }

    public void showViewByTag(String tag){
        int chosen = adapter.getIndexByTag(tag);
        if(viewPager.getCurrentItem()!=chosen)
            viewPager.setCurrentItem(chosen);
    }

    public void reload(){}


}



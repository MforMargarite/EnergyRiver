package com.whucs.energyriver.Widget;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.whucs.energyriver.Adapter.StateSwitchPagerAdapter;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.R;

public class StateSwitchFragment extends Fragment{
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
        adapter.setErrorLayout(initErrorWidget(adapter.getViewByType(Common.ERROR)));
        viewPager.setAdapter(adapter);
    }

    public View initErrorWidget(View view){
        TextView reload = (TextView) view.findViewById(R.id.reload);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showContent();
                reload();
            }
        });
        return view;
    }

    public void showLoading(){
        if(viewPager.getCurrentItem()!=Common.LOADING)
            viewPager.setCurrentItem(Common.LOADING);
    }

    public void showError(){
        if(viewPager.getCurrentItem()!=Common.ERROR)
            viewPager.setCurrentItem(Common.ERROR);
    }

    public void showContent(){
        if(viewPager.getCurrentItem()!=Common.CONTENT)
            viewPager.setCurrentItem(Common.CONTENT);
    }

    public void reload(){}
    }



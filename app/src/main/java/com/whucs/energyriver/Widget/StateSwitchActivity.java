package com.whucs.energyriver.Widget;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.whucs.energyriver.Adapter.StateSwitchPagerAdapter;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.R;


public class StateSwitchActivity extends Activity {
    ScrollForbidViewPager viewPager;
    StateSwitchPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_switch);
        viewPager = (ScrollForbidViewPager) findViewById(R.id.container);
    }

    public void iniAdapter(View view){
        adapter = new StateSwitchPagerAdapter(this,view);//加载内容区至ViewPager中
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
        viewPager.setCurrentItem(Common.LOADING);
    }

    public void showError(){
        viewPager.setCurrentItem(Common.ERROR);
    }

    public void showContent(){
        viewPager.setCurrentItem(Common.CONTENT);
    }

    public void reload(){}
}

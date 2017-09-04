package com.whucs.energyriver;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.whucs.energyriver.Widget.ScrollListView;
import com.whucs.energyriver.Widget.StateSwitchActivity;


public class NotificationActivity extends StateSwitchActivity implements View.OnClickListener{
    ImageView back;
    ScrollListView notices;
    Resources res;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.notification,null);
        initWidget(view);
    }

    private void initWidget(View view){
        iniAdapter(view);
        res = getResources();
        //初始化控件
        back = (ImageView) view.findViewById(R.id.back);
        notices = (ScrollListView) view.findViewById(R.id.notices);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.back:
                NotificationActivity.this.finish();
                break;
        }
    }
}

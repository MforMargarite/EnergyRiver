package com.whucs.energyriver;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.whucs.energyriver.Widget.StateSwitchActivity;


public class NotificationActivity extends StateSwitchActivity implements View.OnClickListener{
    ImageView back;
    ListView notices;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.self_info,null);
        initWidget(view);
    }

    private void initWidget(View view){
        iniAdapter(view);
        back = (ImageView) view.findViewById(R.id.back);
        notices = (ListView) view.findViewById(R.id.notices);
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

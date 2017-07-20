package com.whucs.energyriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.whucs.energyriver.Widget.StateSwitchActivity;


public class SubManageActivity extends StateSwitchActivity implements View.OnClickListener {
    ImageView back;
    ImageView add;//添加子用户
    ListView subs;//子用户列表

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.sub_manage,null);
        initWidget(view);
    }

    private void initWidget(View view){
        iniAdapter(view);
        back = (ImageView) view.findViewById(R.id.back);
        subs = (ListView) view.findViewById(R.id.subs);
        add = (ImageView) view.findViewById(R.id.add);
        back.setOnClickListener(this);
        add.setOnClickListener(this);
    }

    @Override
    public void reload(){
        //present方法
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.back:
                SubManageActivity.this.finish();
                break;
            case R.id.add:
                Intent intent = new Intent(SubManageActivity.this,AddSubActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}

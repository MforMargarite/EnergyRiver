package com.whucs.energyriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;


public class SubManageActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView back;
    ImageView add;//添加子用户
    ListView subs;//子用户列表
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_manage);
        initWidget();
    }

    private void initWidget(){
        back = (ImageView) findViewById(R.id.back);
        subs = (ListView) findViewById(R.id.subs);
        add = (ImageView) findViewById(R.id.add);
        back.setOnClickListener(this);
        add.setOnClickListener(this);
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

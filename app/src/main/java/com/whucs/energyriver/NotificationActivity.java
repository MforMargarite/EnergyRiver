package com.whucs.energyriver;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;


public class NotificationActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView back;
    ListView notices;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.self_info);
        initWidget();
    }

    private void initWidget(){
        back = (ImageView) findViewById(R.id.back);
        notices = (ListView) findViewById(R.id.notices);
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

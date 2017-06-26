package com.whucs.energyriver;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

/*选择房间*/
public class ChooseRoomActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView back;
    ListView building;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_room);
        initWidget();
    }

    private void initWidget(){
        back = (ImageView) findViewById(R.id.back);
        building = (ListView) findViewById(R.id.building);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                ChooseRoomActivity.this.finish();
                break;
            default:
                break;
        }
    }

}

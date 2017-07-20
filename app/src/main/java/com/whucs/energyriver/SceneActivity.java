package com.whucs.energyriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.whucs.energyriver.Widget.StateSwitchActivity;

//获得当前房间下的场景
public class SceneActivity extends StateSwitchActivity implements View.OnClickListener{
    ListView scenes;
    ImageView add,back;
    TextView room;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.scene,null);
        initWidget(view);
    }

    private void initWidget(View view){
        iniAdapter(view);
        back = (ImageView) view.findViewById(R.id.back);
        add = (ImageView) view.findViewById(R.id.add);
        scenes = (ListView) view.findViewById(R.id.scenes);
        room = (TextView) view.findViewById(R.id.room);

        back.setOnClickListener(this);
        add.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.back:
                SceneActivity.this.finish();
                break;
            case R.id.add:
                Intent intent = new Intent(SceneActivity.this,AddSceneActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}

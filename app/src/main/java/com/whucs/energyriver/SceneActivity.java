package com.whucs.energyriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SceneActivity extends AppCompatActivity implements View.OnClickListener{
    ListView scenes;
    ImageView add,back;
    TextView room;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scene);
        initWidget();
    }

    private void initWidget(){
        back = (ImageView) findViewById(R.id.back);
        add = (ImageView) findViewById(R.id.add);
        scenes = (ListView) findViewById(R.id.scenes);
        room = (TextView) findViewById(R.id.room);

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

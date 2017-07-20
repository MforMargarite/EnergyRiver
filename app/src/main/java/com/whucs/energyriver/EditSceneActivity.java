package com.whucs.energyriver;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.whucs.energyriver.Widget.StateSwitchActivity;

public class EditSceneActivity extends StateSwitchActivity implements View.OnClickListener{
    ListView loops;
    ImageView back;
    TextView save,title;
    EditText scene_name_value;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.edit_scene,null);
        initWidget(view);
    }

    private void initWidget(View view){
        iniAdapter(view);
        back = (ImageView) view.findViewById(R.id.back);
        save = (TextView) view.findViewById(R.id.save);
        loops = (ListView) view.findViewById(R.id.loops);
        title = (TextView) view.findViewById(R.id.title);
        scene_name_value = (EditText) view.findViewById(R.id.scene_name_value);

        back.setOnClickListener(this);
        save.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.back:
                EditSceneActivity.this.finish();
                break;
            case R.id.submit:
                break;
            default:
                break;
        }
    }
}

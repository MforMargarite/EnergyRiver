package com.whucs.energyriver;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class AddSceneActivity extends AppCompatActivity implements View.OnClickListener{
    ListView loops;
    ImageView back;
    TextView save,title;
    EditText scene_name_value;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_scene);
        initWidget();
    }

    private void initWidget(){
        back = (ImageView) findViewById(R.id.back);
        save = (TextView) findViewById(R.id.save);
        loops = (ListView) findViewById(R.id.loops);
        title = (TextView) findViewById(R.id.title);
        scene_name_value = (EditText) findViewById(R.id.scene_name_value);

        back.setOnClickListener(this);
        save.setOnClickListener(this);
        title.setText(getResources().getText(R.string.add_scene));
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.back:
                AddSceneActivity.this.finish();
                break;
            case R.id.submit:
                break;
            default:
                break;
        }
    }
}

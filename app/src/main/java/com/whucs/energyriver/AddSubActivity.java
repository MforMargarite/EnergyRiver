package com.whucs.energyriver;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class AddSubActivity extends AppCompatActivity implements View.OnClickListener{
    ListView building;
    ImageView back;
    TextView save,title;
    TextView room_value;
    EditText sub_username_value,work_num_value;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_sub);
        initWidget();
    }

    private void initWidget(){
        back = (ImageView) findViewById(R.id.back);
        save = (TextView) findViewById(R.id.save);
        building = (ListView) findViewById(R.id.building);
        title = (TextView) findViewById(R.id.title);
        sub_username_value = (EditText) findViewById(R.id.sub_username_value);
        work_num_value = (EditText) findViewById(R.id.work_num_value);
        room_value = (TextView) findViewById(R.id.room_value);
        title = (TextView) findViewById(R.id.title);

        back.setOnClickListener(this);
        save.setOnClickListener(this);
        title.setText(getResources().getText(R.string.add_sub));
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.back:
                AddSubActivity.this.finish();
                break;
            case R.id.submit:
                break;
            default:
                break;
        }
    }
}

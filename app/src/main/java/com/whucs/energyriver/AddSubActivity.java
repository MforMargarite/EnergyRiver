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

public class AddSubActivity extends StateSwitchActivity implements View.OnClickListener{
    ListView building;
    ImageView back;
    TextView save,title;
    TextView room_value;
    EditText sub_username_value,work_num_value;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.edit_sub,null);
        initWidget(view);
    }

    private void initWidget(View view){
        iniAdapter(view);
        back = (ImageView) view.findViewById(R.id.back);
        save = (TextView) view.findViewById(R.id.save);
        building = (ListView) view.findViewById(R.id.building);
        title = (TextView) view.findViewById(R.id.title);
        sub_username_value = (EditText) view.findViewById(R.id.sub_username_value);
        work_num_value = (EditText) view.findViewById(R.id.work_num_value);
        room_value = (TextView) view.findViewById(R.id.room_value);
        title = (TextView) view.findViewById(R.id.title);

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

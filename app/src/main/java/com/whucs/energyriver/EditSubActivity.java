package com.whucs.energyriver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.whucs.energyriver.Presenter.EditUserManagePresenter;


public class EditSubActivity extends AppCompatActivity implements View.OnClickListener{
    ListView building;
    ImageView back;
    TextView save,title;
    TextView room_value;
    EditText sub_username_value;//子用户名
    EditText password_value;//初始密码
    EditText department_value;//部门
    EditText job_value;//职位
    EditText mobile_value;//手机号
    EditText identity_value;//身份证
    EditText work_num_value;//工位号
    LinearLayout choose_room;//修改房间
    TextView deleteUser;
    ProgressDialog waiting;
    EditUserManagePresenter presenter;
    Long buildingID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_sub);
        initWidget();
    }

    private void initWidget(){
        back = (ImageView)findViewById(R.id.back);
        save = (TextView)findViewById(R.id.save);
        building = (ListView)findViewById(R.id.building);
        title = (TextView)findViewById(R.id.title);
        sub_username_value = (EditText) findViewById(R.id.sub_username_value);
        work_num_value = (EditText) findViewById(R.id.work_num_value);
        password_value = (EditText) findViewById(R.id.password_value);
        department_value = (EditText) findViewById(R.id.department_value);
        job_value = (EditText) findViewById(R.id.job_value);
        mobile_value = (EditText) findViewById(R.id.mobile_value);
        identity_value = (EditText) findViewById(R.id.identity_value);
        room_value = (TextView) findViewById(R.id.room_value);
        title = (TextView) findViewById(R.id.title);
        choose_room = (LinearLayout) findViewById(R.id.choose_room);
        deleteUser = (TextView) findViewById(R.id.delete_user);

        back.setOnClickListener(this);
        save.setOnClickListener(this);

        Intent intent = getIntent();
        if(intent!=null){
            sub_username_value.setText(intent.getStringExtra("userName"));
            work_num_value.setText(intent.getStringExtra("workNum"));
            department_value.setText(intent.getStringExtra("department"));
            job_value.setText(intent.getStringExtra("job"));
            mobile_value.setText(intent.getStringExtra("mobile"));
            identity_value.setText(intent.getStringExtra("IDNum"));
           // room_value.setText(intent.getLongExtra("buildingID"));
            buildingID = intent.getLongExtra("buildingID",-1L);
        }
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.back:
                EditSubActivity.this.finish();
                break;
            case R.id.submit:
                break;
            default:
                break;
        }
    }
}

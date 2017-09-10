package com.whucs.energyriver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.whucs.energyriver.Bean.SubUser;
import com.whucs.energyriver.Presenter.EditUserManagePresenter;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.Public.ValidStandard;
import com.whucs.energyriver.View.EditUserView;


public class EditSubActivity extends AppCompatActivity implements View.OnClickListener,EditUserView{
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
    Resources res;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_sub);
        initWidget();
    }

    private void initWidget(){
        back = (ImageView)findViewById(R.id.back);
        save = (TextView)findViewById(R.id.save);
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
            Log.e("what",intent.getStringExtra("workNum")+" "+intent.getStringExtra("department"));
            sub_username_value.setText(intent.getStringExtra("userName"));
            work_num_value.setText(intent.getStringExtra("workNum"));
            department_value.setText(intent.getStringExtra("department"));
            job_value.setText(intent.getStringExtra("job"));
            mobile_value.setText(intent.getStringExtra("mobile"));
            identity_value.setText(intent.getStringExtra("IDNum"));
            room_value.setText(intent.getStringExtra("buildingName"));
            buildingID = intent.getLongExtra("buildingID",-1L);
        }

        res = getResources();
        presenter = new EditUserManagePresenter(this);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.back:
                EditSubActivity.this.finish();
                break;
            case R.id.submit:
                if(checkInput())
                    presenter.addSubUser(this);
                break;
            default:
                break;
        }
    }

    private boolean checkInput() {
        boolean isReady = true;
        if (!ValidStandard.isLegal(sub_username_value) || !ValidStandard.isLegal(password_value) || !ValidStandard.isLegal(department_value)
                || !ValidStandard.isLegal(job_value) || !ValidStandard.isLegal(work_num_value) && buildingID!=null ) {
            Toast.makeText(this,res.getText(R.string.empty_input),Toast.LENGTH_SHORT).show();
            isReady = false;
        }
        if(!ValidStandard.isIDCard(identity_value.getText().toString())) {
            Toast.makeText(this,res.getText(R.string.illegal_id),Toast.LENGTH_SHORT).show();
            isReady = false;
        }
        if(!ValidStandard.isMobile(mobile_value.getText().toString())) {
            Toast.makeText(this,res.getText(R.string.illegal_mobile),Toast.LENGTH_SHORT).show();
            isReady = false;
        }
        return isReady;
    }

    @Override
    public void showWaiting() {
        if(waiting == null){
            waiting = new ProgressDialog(this);
        }
        waiting.show();
        waiting.setContentView(R.layout.progress_dialog);
    }

    @Override
    public void hideWaiting() {
        if(waiting!=null && waiting.isShowing())
            waiting.dismiss();
    }

    @Override
    public SubUser getSubUser() {
        SubUser subUser = new SubUser();
        subUser.setUserName(sub_username_value.getText().toString());
        subUser.setInitPassword(password_value.getText().toString());
        subUser.setDepartment(department_value.getText().toString());
        subUser.setWorkNum(work_num_value.getText().toString());
        subUser.setJob(job_value.getText().toString());
        subUser.setBuildingID(buildingID);
        subUser.setCreatorID(Common.getID(this));
        subUser.setMobile(mobile_value.getText().toString());
        subUser.setIdentity(identity_value.getText().toString());
        return subUser;
    }

    @Override
    public void execSuccess(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        EditSubActivity.this.finish();
    }

    @Override
    public void execError(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}

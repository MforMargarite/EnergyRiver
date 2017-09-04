package com.whucs.energyriver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.whucs.energyriver.Presenter.ChangePwdPresenter;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.View.ChangePwdView;


public class ChangePwdActivity extends AppCompatActivity implements View.OnClickListener,ChangePwdView{
    EditText ori_pwd, new_pwd, ensure_new_pwd;
    TextView submit;
    ImageView back;
    ChangePwdPresenter presenter;
    ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pwd);
        initWidget();
    }

    private void initWidget() {
        ori_pwd = (EditText) findViewById(R.id.ori_pwd);
        new_pwd = (EditText) findViewById(R.id.new_pwd);
        ensure_new_pwd = (EditText) findViewById(R.id.ensure_new_pwd);
        submit = (TextView) findViewById(R.id.submit);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        submit.setOnClickListener(this);

        presenter = new ChangePwdPresenter(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit:
                presenter.changePwd(ChangePwdActivity.this);
                break;
            case R.id.back:
                ChangePwdActivity.this.finish();
                break;
        }
    }

    @Override
    public void showWaiting() {
        if(dialog == null){
            dialog = new ProgressDialog(this);
        }
        dialog.show();
        dialog.setContentView(R.layout.progress_dialog);
    }

    @Override
    public void hideWaiting() {
        if(dialog!=null && dialog.isShowing())
            dialog.dismiss();
    }

    @Override
    public String getUsername() {
        return Common.getUserName(this);
    }

    @Override
    public String getPwd() {
        return ori_pwd.getText().toString();
    }

    @Override
    public String getNewPwd() {
        return new_pwd.getText().toString();
    }

    @Override
    public void changePwdSuccess() {
        Toast.makeText(this,"密码修改成功,请重新登陆",Toast.LENGTH_SHORT).show();
          Common.unLogUser(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ChangePwdActivity.this,LogActivity.class);
                startActivity(intent);
            }
        },1000);
        ChangePwdActivity.this.finish();

    }

    @Override
    public void execError(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}

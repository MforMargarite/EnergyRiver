package com.whucs.energyriver;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class ChangePwdActivity extends AppCompatActivity implements View.OnClickListener{
    EditText ori_pwd, new_pwd, ensure_new_pwd;
    TextView submit;
    ImageView back;

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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit:
                break;
            case R.id.back:
                ChangePwdActivity.this.finish();
                break;
        }
    }
}

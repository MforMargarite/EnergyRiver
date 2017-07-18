package com.whucs.energyriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.whucs.energyriver.Bean.User;
import com.whucs.energyriver.Presenter.LogPresenter;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.Public.Layout;
import com.whucs.energyriver.View.LogView;

public class LogActivity extends AppCompatActivity implements View.OnClickListener,LogView{
    private EditText username,password;
    private PercentRelativeLayout submit;
    private ProgressBar progressBar;
    private LogPresenter logPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initWidget();
    }

    private void initWidget(){
        Layout.setTranslucent(this);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        submit = (PercentRelativeLayout) findViewById(R.id.submit);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        submit.setOnClickListener(this);

        logPresenter = new LogPresenter(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit:
                logPresenter.login(LogActivity.this);
                break;
            default:
                break;
        }
    }

    @Override
    public void showWaiting() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideWaiting() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public String getUsername() {
        return username.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public void setUser(User user) {
        //将用户信息保存至SharedPreference中
        Common.setUser(this,user);
        //跳转至主界面
        Toast.makeText(LogActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LogActivity.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginError(String msg) {
        Toast.makeText(LogActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}

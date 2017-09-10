package com.whucs.energyriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.whucs.energyriver.Bean.SubUser;
import com.whucs.energyriver.Bean.User;
import com.whucs.energyriver.Presenter.LogPresenter;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.Public.Layout;
import com.whucs.energyriver.View.LogView;
import com.whucs.energyriver.Widget.AvatarImageView;

public class LogActivity extends AppCompatActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener, LogView {
    private EditText username, password;
    private PercentRelativeLayout submit;
    private ProgressBar progressBar;
    private LogPresenter logPresenter;
    private AppCompatCheckBox checkBox;
    private AvatarImageView avatar;
    private boolean isSubVIPLogin;//是否为VIP子用户登陆

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initWidget();
    }

    private void initWidget() {
        Layout.setTranslucent(this);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        submit = (PercentRelativeLayout) findViewById(R.id.submit);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        checkBox = (AppCompatCheckBox) findViewById(R.id.checkbox);
        avatar = (AvatarImageView) findViewById(R.id.avatar);
        submit.setOnClickListener(this);

        logPresenter = new LogPresenter(this);
        checkBox.setOnCheckedChangeListener(this);

        isSubVIPLogin = false;
        if(Common.hasLastAvatar(this))
            avatar.setImageBitmap(Common.getLastAvatar(this));
        if(Common.getSharedPreference(this).contains("lastUserName"))
            username.setText(Common.getSharedPreference(this).getString("lastUserName",""));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit:
                if(!isSubVIPLogin)
                    logPresenter.login(LogActivity.this);
                else
                    logPresenter.subLogin(LogActivity.this);
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
        try {
            //将用户信息保存至SharedPreference中
            Common.setUser(this, user,getPassword());
            //跳转至主界面
            Toast.makeText(LogActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LogActivity.this, MainActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(LogActivity.this, "保存用户信息失败,请重试", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void setSubUser(SubUser user) {
        try {
            Common.setSubUser(this,user);
            Toast.makeText(LogActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LogActivity.this, MainActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(LogActivity.this, "保存用户信息失败,请重试", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void loginError(String msg) {
        Toast.makeText(LogActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
       isSubVIPLogin = isChecked;
    }
}

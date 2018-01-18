package com.whucs.energyriver;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.Widget.AvatarImageView;


public class UserFragment extends Fragment implements View.OnClickListener{
    PercentRelativeLayout user_panel;//个人信息
    PercentRelativeLayout sub_manage;//子用户管理
    PercentRelativeLayout bill;//账单管理
    PercentRelativeLayout notification;//事件通知
    PercentRelativeLayout buy_vip;//VIP购买
    PercentRelativeLayout invoice_manage;//发票管理
    PercentRelativeLayout change_pwd;//修改密码
    PercentRelativeLayout log_out;//退出登录
    PercentRelativeLayout about_us;//关于我们
    AvatarImageView avatar;
    TextView username,score;
    Activity activity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user,null);
        initWidget(view);
        return view;
    }

    private void initWidget(View view){
        activity = getActivity();
        avatar = (AvatarImageView) view.findViewById(R.id.avatar);
        username = (TextView) view.findViewById(R.id.username);
        score = (TextView)view.findViewById(R.id.score);

        user_panel = (PercentRelativeLayout) view.findViewById(R.id.user_panel);
        sub_manage = (PercentRelativeLayout) view.findViewById(R.id.sub_manage);
        bill = (PercentRelativeLayout) view.findViewById(R.id.bill);
        notification = (PercentRelativeLayout) view.findViewById(R.id.notification);
        buy_vip = (PercentRelativeLayout) view.findViewById(R.id.buy_vip);
        invoice_manage = (PercentRelativeLayout) view.findViewById(R.id.invoice_manage);
        change_pwd = (PercentRelativeLayout) view.findViewById(R.id.change_pwd);
        log_out = (PercentRelativeLayout) view.findViewById(R.id.log_out);
        about_us = (PercentRelativeLayout) view.findViewById(R.id.about_us);

        user_panel.setOnClickListener(this);
        sub_manage.setOnClickListener(this);
        bill.setOnClickListener(this);
        notification.setOnClickListener(this);
        change_pwd.setOnClickListener(this);
        log_out.setOnClickListener(this);
        about_us.setOnClickListener(this);


    }

    @Override
    public void onResume() {
        super.onResume();
        //初始化用户头像、用户名和积分信息
        if(Common.hasAvatar(activity))
            avatar.setImageBitmap(Common.getAvatar(activity));
        username.setText(Common.getUserName(activity));
        score.setText("积分: "+Common.getScore(activity));
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch(view.getId()){
            case R.id.user_panel:
                intent = new Intent(activity,UserInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.sub_manage:
                intent = new Intent(activity,SubManageActivity.class);
                startActivity(intent);
                break;
            case R.id.bill:
                intent = new Intent(activity,BillActivity.class);
                startActivity(intent);
                break;
            case R.id.notification:
                intent = new Intent(activity,NotificationActivity.class);
                startActivity(intent);
                break;
            case R.id.change_pwd:
                intent = new Intent(activity,ChangePwdActivity.class);
                startActivity(intent);
                break;
            case R.id.log_out:
                logOut();
                break;
            case R.id.about_us:
                intent = new Intent(activity,AboutUs.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }


    private void logOut(){
        Common.unLogUser(activity);
        Toast.makeText(activity,activity.getResources().getText(R.string.log_out_success),Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(activity,LogActivity.class);
                startActivity(intent);
            }
        },1000);
        }
}

package com.whucs.energyriver;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.whucs.energyriver.Public.Common;

import java.io.File;


public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener{
    private PercentRelativeLayout avatar_panel,name_panel;
    private ImageView avatar,back;
    private TextView username;
    private Intent intent;
    private File tempFile;
    private PopupWindow popupWindow;
    private static final int PHOTO_REQUEST_SHOOT = 0;
    private static final int PHOTO_REQUEST_PHOTOGRAPH = 1;
    private static final String ROOT = "EnergyRiver";
    private static final String AVATAR = "avatar";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.self_info);
        initWidget();
    }

    private void initWidget(){
        avatar_panel = (PercentRelativeLayout) findViewById(R.id.avatar_panel);
        name_panel = (PercentRelativeLayout) findViewById(R.id.name_panel);
        avatar = (ImageView) findViewById(R.id.avatar);
        back = (ImageView) findViewById(R.id.back);
        username = (TextView) findViewById(R.id.username);
        avatar_panel.setOnClickListener(this);
        name_panel.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.back:
                UserInfoActivity.this.finish();
                break;
            case R.id.avatar_panel:
                showPopupWindow(view);
                break;
            case R.id.name_panel:
                break;
            case R.id.fromCamera:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                tempFile = new File(File.separator + Environment.getExternalStorageDirectory() + File.separator + ROOT + File.separator + AVATAR + File.separator, Common.getPhotoFileName());
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                startActivityForResult(intent, PHOTO_REQUEST_SHOOT);
                break;
            case R.id.fromGallery:
                intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, PHOTO_REQUEST_PHOTOGRAPH);
                break;
            case R.id.avatar_click:
                popupWindow.dismiss();
                break;

        }
    }

    private void showPopupWindow(View view) {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.pop_window, null);
        // 设置按钮的点击事件
        LinearLayout avatar_click = (LinearLayout) contentView.findViewById(R.id.avatar_click);
        TextView fromGallery = (TextView) contentView.findViewById(R.id.fromGallery);
        TextView fromCamera = (TextView) contentView.findViewById(R.id.fromCamera);
        avatar_click.setOnClickListener(this);
        fromCamera.setOnClickListener(this);
        fromGallery.setOnClickListener(this);
        fromGallery.setOnClickListener(this);
        popupWindow = new PopupWindow(contentView,
                PercentRelativeLayout.LayoutParams.MATCH_PARENT, PercentRelativeLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setTouchable(true);
        popupWindow.showAtLocation(view, Gravity.CENTER_VERTICAL,0,0);
    }

}

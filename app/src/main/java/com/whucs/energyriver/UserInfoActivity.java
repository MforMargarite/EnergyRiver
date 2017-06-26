package com.whucs.energyriver;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.whucs.energyriver.Widget.MyCircleCrop;

import java.io.File;


public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener{
    private PercentRelativeLayout avatar_panel,name_panel;
    private ImageView avatar,back;
    private TextView username;
    private Intent intent;
    private File tempFile;
    private PopupWindow popupWindow;
    private static final int PHOTO_REQUEST_SHOOT = 1;
    private static final int PHOTO_REQUEST_PHOTOGRAPH = 2;
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
        if(Common.hasAvatar())
            avatar.setImageBitmap(Common.getAvatar());
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
                intent = new Intent(UserInfoActivity.this,ChangeNameActivity.class);
                startActivity(intent);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    final Uri imgUrl = Uri.fromFile(tempFile);
                    intent = new Intent(this, MyCircleCrop.class);
                    intent.putExtra("data", imgUrl);
                    startActivityForResult(intent, 3);
                }
                break;
            case 2:
                if (data != null) {
                    //判断mode是否为-1 -1表示未选择返回
                    if (data.getIntExtra("mode", 0) != -1) {
                        final Uri photoUrl = data.getData();
                        intent = new Intent(this, MyCircleCrop.class);
                        intent.putExtra("data", photoUrl);
                        startActivityForResult(intent, 3);
                        break;
                    }
                }
                break;
            case 3:
                if (data != null) {
                    if (data.getIntExtra("mode", 0) != -1) {
                        try {
                            byte[] avatar_byte = data.getByteArrayExtra("crop_pic");
                            //post 更新至服务器 若成功
                            Bitmap bmp = BitmapFactory.decodeByteArray(avatar_byte,0,avatar_byte.length);
                            avatar.setImageBitmap(bmp);
                            Common.setAvatar(bmp);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
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

package com.whucs.energyriver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.whucs.energyriver.Presenter.UserInfoPresenter;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.View.UserInfoView;
import com.whucs.energyriver.Widget.MyCircleCrop;
import java.io.File;


public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener,UserInfoView{
    private PercentRelativeLayout avatar_panel,name_panel;
    private ImageView avatar,back;
    private TextView username;
    private Intent intent;
    private File tempFile;
    private PopupWindow popupWindow;
    private static final int PHOTO_REQUEST_SHOOT = 1;
    private static final int PHOTO_REQUEST_PHOTOGRAPH = 2;
    private static final int CHANGE_USERNAME = 4;
    private static final String ROOT = "EnergyRiver";
    private static final String AVATAR = "avatar";
    private UserInfoPresenter presenter;
    private byte[] avatar_byte;         //新头像
    private String username_value;      //新用户名
    private String avatar_url;          //新头像地址
    private ProgressDialog dialog;      //加载中悬浮窗

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
        //初始化 加载头像和用户名
        if(Common.hasAvatar(this))
            avatar.setImageBitmap(Common.getAvatar(this));
        username.setText(Common.getUserName(this));
        //初始化presenter
        presenter = new UserInfoPresenter(this);
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
                startActivityForResult(intent,CHANGE_USERNAME);
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
            case R.id.cancel:
                if(popupWindow.isShowing())
                    popupWindow.dismiss();
                break;
            case R.id.avatar_click:
                if(popupWindow.isShowing())
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
                            avatar_byte = data.getByteArrayExtra("crop_pic");
                            if(avatar_byte!=null)
                                presenter.uploadAvatar(this);//post 更新至服务器
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case 4:
                if (data != null) {
                    if (resultCode == 1) {
                        try {
                            username_value = data.getStringExtra("username").trim();
                            if(!username_value.equals(username.getText().toString()))//用户名改变了
                               presenter.updateUsername(this);//post 更新至服务器
                        } catch (Exception e) {
                            Log.e("what",e.getMessage());
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
        TextView cancel = (TextView) contentView.findViewById(R.id.cancel);
        avatar_click.setOnClickListener(this);
        fromCamera.setOnClickListener(this);
        fromGallery.setOnClickListener(this);
        cancel.setOnClickListener(this);
        popupWindow = new PopupWindow(contentView,
                PercentRelativeLayout.LayoutParams.MATCH_PARENT, PercentRelativeLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setTouchable(true);
        popupWindow.showAtLocation(view, Gravity.CENTER_VERTICAL,0,0);
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
    public byte[] getAvatarData() {
        return avatar_byte;
    }

    @Override
    public String getUsername() {
        return username_value;
    }

    @Override
    public void updateURLSuccess(byte[] avatar_byte){
        Bitmap bmp = BitmapFactory.decodeByteArray(avatar_byte,0,avatar_byte.length);
        avatar.setImageBitmap(bmp);
        Common.saveAvatar(this,bmp);

    }

    @Override
    public void uploadAvatarSuccess(String url) {
        //更新用户头像
        if(popupWindow.isShowing())
            popupWindow.dismiss();
        avatar_url = url;
        presenter.updateAvatarURL(this);
    }

    @Override
    public String getAvatarURL(){
        return avatar_url;
    }

    @Override
    public void uploadUsernameSuccess(String username_value){
        username.setText(username_value);
        Common.saveUserName(this,username_value);
    }

    @Override
    public void execError(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}

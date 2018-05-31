package com.whucs.energyriver.Service;


import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.whucs.energyriver.Public.Common;

import java.io.File;


public class UpdateService extends Service {
    /** 安卓系统下载类 **/
    DownloadManager manager;
    /** 接收下载完的广播 **/
    DownloadCompleteReceiver receiver;
    /** 初始化下载器 **/
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:Toast toast = Toast.makeText(getApplicationContext(), "下载失败，请检查网络后重试", Toast.LENGTH_LONG);
                    toast.show();
                    break;
            }
        }
    };

    private void initDownManager() {
        final File rootFile = new File(File.separator+ Environment.getExternalStorageDirectory()+File.separator+"EnergyRiver"+File.separator);
        Log.e("what",rootFile.getAbsolutePath());
        Log.e("what",Common.getLatestUrl());
        if (!rootFile.exists()) {
            rootFile.mkdir();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                    //设置下载地址
                    DownloadManager.Request down = new DownloadManager.Request(
                            Uri.parse(Common.getLatestUrl()));
                    // 设置允许使用的网络类型，这里是移动网络和wifi都可以
                    down.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE
                            | DownloadManager.Request.NETWORK_WIFI);
                    // 下载时，通知栏显示途中
                    down.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                    // 显示下载界面
                    down.setVisibleInDownloadsUi(true);
                    // 设置下载后文件存放的位置
                    down.setDestinationInExternalFilesDir(UpdateService.this, rootFile.getAbsolutePath(), "EnergyRiver_" + Common.getLatestVersion() + ".apk");
                    // 将下载请求放入队列
                    manager.enqueue(down);
                    //注册下载广播
                }catch (Exception e){
                    e.printStackTrace();
                    Message message = handler.obtainMessage();
                    message.what = 1;
                    handler.sendMessage(message);
                }
            }
        }).start();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        receiver = new DownloadCompleteReceiver();
        registerReceiver(receiver, new IntentFilter(
                DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 调用下载
        initDownManager();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        // 注销下载广播
        if (receiver != null)
            unregisterReceiver(receiver);
        super.onDestroy();
    }

    // 接受下载完成后的intent
    class DownloadCompleteReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //判断是否下载完成的广播
            if (intent.getAction().equals(
                    DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                //获取下载的文件id
                long downId = intent.getLongExtra(
                        DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                //自动安装apk
                installAPK(manager.getUriForDownloadedFile(downId));
                //停止服务并关闭广播
            }
        }

        /**
         * 安装apk文件
         */
        private void installAPK(Uri apk) {
            // 通过Intent安装APK文件
            Intent intents = new Intent();
            intents.setAction("android.intent.action.VIEW");
            intents.addCategory("android.intent.category.DEFAULT");
            intents.setType("application/vnd.android.package-archive");
            intents.setData(apk);
            intents.setDataAndType(apk, "application/vnd.android.package-archive");
            intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intents);
            UpdateService.this.stopSelf();
        }
    }
}

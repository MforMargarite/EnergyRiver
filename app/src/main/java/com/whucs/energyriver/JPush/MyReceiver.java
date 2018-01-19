package com.whucs.energyriver.JPush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.whucs.energyriver.NotificationActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * 接收推送执行notificationReceived notificationClicked等回调函数
 */
public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "JPUSH";

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			//点击通知打开页面的参数
            Bundle bundle = intent.getExtras();

			if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
				String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
				//send the Registration Id to your server...

			}  else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
				Log.d(TAG, "[MyReceiver] 接收到推送下来的通知");
				int notificationId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
				Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notificationId);

			} else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
				//打开事件通知Activity
				Intent i = new Intent(context, NotificationActivity.class);
				i.putExtras(bundle);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
				context.startActivity(i);
			} else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
				Log.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
				//在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
			} else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
				boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
				Log.w(TAG, "[MyReceiver]" + intent.getAction() +" connected state change to "+connected);
			} else {
				Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
			}
		} catch (Exception e){
            e.printStackTrace();
		}
	}

}

package com.whucs.energyriver.JPush;

import android.content.Context;

import com.whucs.energyriver.MainActivity;
import com.whucs.energyriver.Public.Common;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.service.JPushMessageReceiver;


public class JPushReceiver extends JPushMessageReceiver {
    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onAliasOperatorResult(context, jPushMessage);
    }


}

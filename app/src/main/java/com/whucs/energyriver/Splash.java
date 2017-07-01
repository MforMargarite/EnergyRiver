package com.whucs.energyriver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.Public.Layout;


public class Splash extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Layout.setTranslucent(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if(Common.getID(Splash.this) == -1L)
                    intent = new Intent(Splash.this, LogActivity.class);
                else
                    intent = new Intent(Splash.this,MainActivity.class);
                startActivity(intent);
            }
        },1000);
    }


}

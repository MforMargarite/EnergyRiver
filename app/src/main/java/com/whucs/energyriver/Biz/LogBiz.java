package com.whucs.energyriver.Biz;


import android.content.Context;

import com.whucs.energyriver.Bean.HttpResult;
import com.whucs.energyriver.Bean.User;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.Public.HttpUtil;

import okhttp3.Cookie;
import okhttp3.Interceptor;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public class LogBiz {
    interface LogService {
        @POST("/user/mobileLogin")
        Observable<HttpResult<User>> login(@Query("userName") String username, @Query("password") String password);
    }


    public Observable<HttpResult<User>> login(Context context, String username, String password){
        return Common.getRetrofit(context).create(LogService.class).login(username,password);
    }
}

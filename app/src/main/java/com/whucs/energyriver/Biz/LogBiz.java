package com.whucs.energyriver.Biz;


import com.whucs.energyriver.Bean.User;
import com.whucs.energyriver.Public.Common;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public class LogBiz {
    interface LogService {
        @POST("/login")
        Observable<User> login(@Query("username") String username, @Query("password") String password);
    }

    public Observable<User> login(String username,String password){
        return Common.getRetrofit().create(LogService.class).login(username,password);
    }
}

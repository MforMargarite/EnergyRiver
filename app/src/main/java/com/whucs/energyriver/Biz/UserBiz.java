package com.whucs.energyriver.Biz;


import android.content.Context;
import com.whucs.energyriver.Bean.HttpCode;
import com.whucs.energyriver.Bean.HttpResult;
import com.whucs.energyriver.Bean.PwdState;
import com.whucs.energyriver.Bean.User;
import com.whucs.energyriver.Public.Common;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public class UserBiz {
    private static UserService userService;

    public static UserService getUserService(Context context){
        if(userService == null)
             userService = Common.getRetrofit(context).create(UserService.class);
        return userService;
    }

    interface UserService {
        @POST("user/mobileLogin")
        Observable<User> login(@Query("userName") String username, @Query("password") String password);

        @FormUrlEncoded
        @POST("usermanager/updateUser")
        Observable<HttpCode> updateUserName(@Field("userName") String userName,@Field("userID") Long userID); //参数为json字符串

        @POST("usermanager/saveHeadImg")
        Observable<HttpResult> uploadAvatar(@Query("data") String data); //参数为???

        @POST("usermanager/changepwd")
        Observable<PwdState> changePwd(@Query("userName") String userName, @Query("password") String password, @Query("newpassword") String newpassword);

    }


    public Observable<User> login(Context context, String username, String password){
        return getUserService(context).login(username,password);
    }

    public Observable<HttpCode> updateUserName(Context context,String username,Long userID){
        return getUserService(context).updateUserName(username,userID);
    }



    public Observable<HttpResult> uploadAvatar(Context context,String data){
        return getUserService(context).uploadAvatar(data);
    }

    public Observable<PwdState> changePwd(Context context, String userName, String password, String newpassword){
        return getUserService(context).changePwd(userName,password,newpassword);
    }


}

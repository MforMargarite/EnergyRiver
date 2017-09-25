package com.whucs.energyriver.Biz;


import android.content.Context;
import com.whucs.energyriver.Bean.HttpCode;
import com.whucs.energyriver.Bean.HttpData;
import com.whucs.energyriver.Bean.HttpResult;
import com.whucs.energyriver.Bean.HttpURL;
import com.whucs.energyriver.Bean.PwdState;
import com.whucs.energyriver.Bean.SubUser;
import com.whucs.energyriver.Bean.User;
import com.whucs.energyriver.Public.Common;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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

        @POST("user/mobileSubLogin")
        Observable<SubUser> subLogin(@Query("userName") String username, @Query("password") String password);

        @FormUrlEncoded
        @POST("usermanager/updateUser")
        Observable<HttpCode> updateUserName(@Field("userName") String userName,@Field("userID") Long userID,@Field("branchID")Long branchID); //参数为json字符串

        @FormUrlEncoded
        @POST("usermanager/saveHeadImg")
        Observable<HttpURL> uploadAvatar(@Field("data") String data); //参数为Base64加密的图片流

        @POST("usermanager/changepwd")
        Observable<PwdState> changePwd(@Query("userName") String userName, @Query("password") String password, @Query("newpassword") String newpassword);

        @FormUrlEncoded
        @POST("usermanager/updateUser")
        Observable<HttpCode> updateMobile(@Field("telephone") String mobile,@Field("userID") Long userID,@Field("branchID")Long branchID); //参数为json字符串


        @FormUrlEncoded
        @POST("usermanager/updateUser")
        Observable<HttpCode> updateAvatarURL(@Field("headImg") String avatar_url,@Field("userID") Long userID,@Field("branchID")Long branchID);
     }





    public Observable<User> login(Context context, String username, String password){
        return getUserService(context).login(username,password);
    }

    public Observable<SubUser> subLogin(Context context, String username, String password){
        return getUserService(context).subLogin(username,password);
    }

    public Observable<HttpCode> updateUserName(Context context,String username,Long userID,Long branchID){
        return getUserService(context).updateUserName(username,userID,branchID);
    }

    public Observable<HttpCode> updateMobile(Context context,String mobile,Long userID,Long branchID){
        return getUserService(context).updateMobile(mobile,userID,branchID);
    }

    public Observable<HttpCode> updateAvatarURL(Context context,String avatar_url,Long userID,Long branchID){
        return getUserService(context).updateAvatarURL(avatar_url,userID,branchID);
    }

    public Observable<HttpURL> uploadAvatar(Context context,String data){
        return getUserService(context).uploadAvatar(data);
    }

    public Observable<PwdState> changePwd(Context context, String userName, String password, String newpassword){
        return getUserService(context).changePwd(userName,password,newpassword);
    }



}

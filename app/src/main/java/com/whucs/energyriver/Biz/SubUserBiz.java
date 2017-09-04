package com.whucs.energyriver.Biz;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.whucs.energyriver.Bean.HttpData;
import com.whucs.energyriver.Bean.HttpResult;
import com.whucs.energyriver.Bean.SubUser;
import com.whucs.energyriver.Public.Common;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

//VIP子用户管理
public class SubUserBiz {
    private static SubUserService userService;

    public static SubUserService getSubUserService(Context context){
        if(userService == null)
            userService = Common.getRetrofit(context).create(SubUserService.class);
        return userService;
    }

    interface SubUserService {
        @GET("usermanager/getSubUsers")
        Observable<HttpData<List<SubUser>>> getSubUser(@Query("userID")Long userID);

        @FormUrlEncoded
        @POST("usermanager/addSubUser")
        Observable<HttpResult> addSubUser(@Field("userName")String userName,@Field("initPassword")String password,@Field("workNum")String workNum,
                                          @Field("department")String department, @Field("job")String job,@Field("mobile")String mobile,
                                          @Field("IDNum")String identity,@Field("creatorID")long creatorID,@Field("buildingID")long buildingID);

        @FormUrlEncoded
        @POST("usermanager/updateSubUser")
        Observable<HttpResult> updateSubUser(@Field("userName")String userName,@Field("initPassword")String password,@Field("workNum")String workNum,
                                          @Field("department")String department, @Field("job")String job,@Field("mobile")String mobile,
                                          @Field("IDNum")String identity,@Field("creatorID")long creatorID,@Field("buildingID")long buildingID);

        @POST("usermanager/deleteSubUser")
        Observable<HttpResult> deleteSubUser(@Query("userID")Long userID);
    }

    public Observable<HttpData<List<SubUser>>> getSubUser(Context context, Long userID){
        return getSubUserService(context).getSubUser(userID);
    }

    public Observable<HttpResult> addSubUser(Context context,SubUser user){
        return getSubUserService(context).addSubUser(user.getUserName(),user.getInitPassword(),user.getWorkNum(),
                                                     user.getDepartment(),user.getJob(),user.getMobile(),
                                                     user.getIdentity(), user.getCreatorID(),user.getBuildingID());
    }

    public Observable<HttpResult> updateSubUser(Context context,SubUser user){
        return getSubUserService(context).addSubUser(user.getUserName(),user.getInitPassword(),user.getWorkNum(),
                user.getDepartment(),user.getJob(),user.getMobile(),
                user.getIdentity(), user.getCreatorID(),user.getBuildingID());
    }

    public Observable<HttpResult> deleteSubUser(Context context,Long userID){
        return getSubUserService(context).deleteSubUser(userID);
    }
}

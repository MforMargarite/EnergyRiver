package com.whucs.energyriver.Biz;

import android.content.Context;

import com.whucs.energyriver.Bean.HttpData;
import com.whucs.energyriver.Bean.HttpResult;
import com.whucs.energyriver.Bean.HttpURL;
import com.whucs.energyriver.Bean.LoopStatus;
import com.whucs.energyriver.Bean.Scene;
import com.whucs.energyriver.Public.Common;

import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

public class SceneBiz {
    private static SceneService service;
    private SceneService getSceneService(Context context){
        if(service == null)
            service = Common.getRetrofit(context).create(SceneService.class);
        return service;
    }

    private interface SceneService {
        @GET("scene/getSceneMode")
        Observable<List<Scene>>getScenes(@Query("UserId")Long userID);

        @GET("scene/getGroupControlInfo")
        Observable<List<Scene>>getGroupControls(@Query("UserId")Long userID);

        @GET("scene/changeSceneMode")
        Observable<Map<String,Boolean>> updateSceneMode(@Query("sceneId")Long sceneID);

        @GET("scene/changeGroupControl")
        Observable<Map<String,Boolean>> updateGroupControlMode(@Query("sceneId")Long sceneID, @Query("isOpen")Integer isOpen);

        @GET("scene/getIsAdmitCutLoopInfo")
        Observable<List<LoopStatus>> getControlLoops(@Query("UserId")Long userID);

        @GET("scene/deleteSceneMode")
        Observable<Map<String,Boolean>> delSceneById(@Query("sceneId")Long sceneId);

        @POST("scene/addSceneMode")
        @FormUrlEncoded
        Observable<HttpURL> addSceneMode(@Field("sceneRelate") String sceneRelate);
    }

    public Observable<Map<String,Boolean>> updateGroupControl(Context context,Long sceneID,Integer isOpen){
        return getSceneService(context).updateGroupControlMode(sceneID,isOpen);
    }

    public Observable<Map<String,Boolean>>updateScene(Context context,Long sceneID){
        return getSceneService(context).updateSceneMode(sceneID);
    }

    public Observable<List<Scene>>getGroupControls(Context context,Long userID){
        return getSceneService(context).getGroupControls(userID);
    }

    public Observable<List<Scene>>getScenes(Context context,Long userID){
        return getSceneService(context).getScenes(userID);
    }

    public Observable<List<LoopStatus>>getControlLoops(Context context,Long userID){
        return getSceneService(context).getControlLoops(userID);
    }

    public Observable<Map<String,Boolean>> delSceneById(Context context,Long sceneID){
        return getSceneService(context).delSceneById(sceneID);
    }

    public Observable<HttpURL>addSceneMode(Context context,String scene){
        return getSceneService(context).addSceneMode(scene);
    }
}

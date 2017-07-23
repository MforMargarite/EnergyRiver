package com.whucs.energyriver.Biz;


import android.content.Context;

import com.whucs.energyriver.Bean.HttpData;
import com.whucs.energyriver.Bean.HttpResult;
import com.whucs.energyriver.Bean.Loop;
import com.whucs.energyriver.Public.Common;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public class LoopBiz {
    public interface LoopService {
        @GET("/loopManage/getLoopInfoByBuildSortByType")
        Observable<HttpData<List<Loop>>> getLoopInfoByBuildID(@Query("buildingID") Long buildingID);

        @POST("/monitor/updateLoopStateByLoopID")
        Observable<HttpResult> updateLoop (@Query("loopID") Long buildingID, @Query("loopState") String loopState);

    }

    public Observable<HttpData<List<Loop>>> getLoopInfoByBuildID(Context context, Long buildingID){
        return Common.getRetrofit(context).create(LoopService.class).getLoopInfoByBuildID(buildingID);
    }

    public Observable<HttpResult> updateLoop (Context context, Long buildingID, String loopState){
        return Common.getRetrofit(context).create(LoopService.class).updateLoop(buildingID,loopState);
    }


}

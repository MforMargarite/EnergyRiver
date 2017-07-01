package com.whucs.energyriver.Biz;


import android.content.Context;

import com.whucs.energyriver.Bean.HttpResult;
import com.whucs.energyriver.Bean.Loop;
import com.whucs.energyriver.Bean.RoomRank;
import com.whucs.energyriver.Bean.User;
import com.whucs.energyriver.Public.Common;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public class LoopBiz {
    public interface LoopService {
        @GET("/loopManage/getLoopInfoByBuildSortByType")
        Observable<HttpResult<List<Loop>>> getLoopInfoByBuildID(@Query("buildingID") Long buildingID);

    }

    public Observable<HttpResult<List<Loop>>> getLoopInfoByBuildID(Context context, Long buildingID){
        return Common.getRetrofit(context).create(LoopService.class).getLoopInfoByBuildID(buildingID);
    }
}

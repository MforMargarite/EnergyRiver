package com.whucs.energyriver.Biz;


import android.content.Context;

import com.whucs.energyriver.Bean.Building;
import com.whucs.energyriver.Bean.HttpResult;
import com.whucs.energyriver.Bean.User;
import com.whucs.energyriver.Public.Common;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public class BuildingBiz {
    public interface BuildingService {
        @POST("/build/getBuildingInfo")
        Observable<HttpResult<List<Building>>> getBuildingInfo(@Query("userID") Long userID);
    }

    public Observable<HttpResult<List<Building>>> getBuildingInfo(Context context, Long userID){
        return Common.getRetrofit(context).create(BuildingService.class).getBuildingInfo(userID);
    }
}

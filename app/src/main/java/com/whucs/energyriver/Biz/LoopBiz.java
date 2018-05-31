package com.whucs.energyriver.Biz;


import android.content.Context;
import com.whucs.energyriver.Bean.ACCollect;
import com.whucs.energyriver.Bean.HttpListData;
import com.whucs.energyriver.Bean.HttpResult;
import com.whucs.energyriver.Bean.Loop;
import com.whucs.energyriver.Bean.LoopStatus;
import com.whucs.energyriver.Public.Common;

import java.util.HashMap;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public class LoopBiz {
    private static LoopService loopService ;

    public static LoopService getLoopService(Context context){
        if(loopService == null)
            loopService = Common.getRetrofit(context).create(LoopService.class);
        return loopService;
    }

    interface LoopService {
        @GET("branch/getControlLoop")
        Observable<List<Loop>> getLoopInfoByBuildID(@Query("buildingID") Long buildingID);

        @GET("monitor/updateOpenStatusByLoopIDPhone")
        Observable<Boolean> updateLoop (@Query("loopID") Long loopID, @Query("openStatus") Integer loopState);

        //建筑ID->回路状态
        @GET("monitor/getLoopStateByBuilding")
        Observable<HttpListData<HashMap<Long,Object>>> getLoopStateByBuilding (@Query("buildingID") Long buildingID);

        //回路ID->回路状态
        @GET("monitor/getLoopStateByLoopID")
        Observable<HttpListData<ACCollect>> getAirLoopStateByID (@Query("loopID") Long loopID);

        //用户ID->对应回路
        @GET("scene/getIsAdmitCutLoopInfo")
        Observable<List<LoopStatus>>getLoopByUser(@Query("UserId") Long userID);

    }

    public Observable<List<Loop>> getLoopInfoByBuildID(Context context, Long buildingID){
        return getLoopService(context).getLoopInfoByBuildID(buildingID);
    }

    public Observable<Boolean> updateLoop (Context context,Long buildingID,Integer openStatus){
        return getLoopService(context).updateLoop(buildingID,openStatus);
    }

    public Observable<HttpListData<HashMap<Long,Object>>> getLoopStateByBuilding (Context context, Long buildingID){
        return getLoopService(context).getLoopStateByBuilding(buildingID);
    }

    public Observable<HttpListData<ACCollect>> getAirLoopStateByID (Context context, Long loopID){
        return getLoopService(context).getAirLoopStateByID(loopID);
    }

    public Observable<List<LoopStatus>>getLoopByUser(Context context, Long userID){
        return getLoopService(context).getLoopByUser(userID);
    }


}

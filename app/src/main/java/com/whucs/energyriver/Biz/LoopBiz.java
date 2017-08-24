package com.whucs.energyriver.Biz;


import android.content.Context;
import com.whucs.energyriver.Bean.ACCollect;
import com.whucs.energyriver.Bean.HttpListData;
import com.whucs.energyriver.Bean.HttpResult;
import com.whucs.energyriver.Bean.Loop;
import java.util.HashMap;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


public class LoopBiz {
    private static LoopService loopService ;

    public static LoopService getLoopService(Context context){
        if(loopService == null)
            loopService = getLoopService(context);
        return loopService;
    }

    interface LoopService {
        @GET("/loopManage/getLoopInfoByBuildSortByType")
        Observable<HttpListData<List<Loop>>> getLoopInfoByBuildID(@Query("buildingID") Long buildingID);

        @POST("/monitor/updateLoopStateByLoopID")
        Observable<HttpResult> updateLoop (@Query("loopID") Long loopID, @Query("loopState") String loopState);

        @GET("/monitor/getLoopStateByBuilding")
        Observable<HttpListData<HashMap<Long,Object>>> getLoopStateByBuilding (@Query("buildingID") Long buildingID);

        @GET("/monitor/getLoopStateByLoopID")
        Observable<HttpListData<ACCollect>> getAirLoopStateByID (@Query("loopID") Long loopID);

    }

    public Observable<HttpListData<List<Loop>>> getLoopInfoByBuildID(Context context, Long buildingID){
        return getLoopService(context).getLoopInfoByBuildID(buildingID);
    }

    public Observable<HttpResult> updateLoop (Context context, Long buildingID, String loopState){
        return getLoopService(context).updateLoop(buildingID,loopState);
    }

    public Observable<HttpListData<HashMap<Long,Object>>> getLoopStateByBuilding (Context context, Long buildingID){
        return getLoopService(context).getLoopStateByBuilding(buildingID);
    }

    public Observable<HttpListData<ACCollect>> getAirLoopStateByID (Context context, Long loopID){
        return getLoopService(context).getAirLoopStateByID(loopID);
    }


}

package com.whucs.energyriver.Biz;

import android.content.Context;
import com.whucs.energyriver.Bean.Building;
import com.whucs.energyriver.Bean.HttpListData;
import com.whucs.energyriver.Public.Common;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import java.util.List;


public class BuildingBiz {
    public interface BuildingService {
        @GET("build/getBuildingInfo")
        Observable<HttpListData<List<Building>>> getBuildingInfo(@Query("userID")Long userID);
    }

    public Observable<HttpListData<List<Building>>> getBuildingInfo(Context context){
        return Common.getRetrofit(context).create(BuildingService.class).getBuildingInfo(Common.getID(context));
    }
}

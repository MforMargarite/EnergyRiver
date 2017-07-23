package com.whucs.energyriver.Biz;

import android.content.Context;

import com.whucs.energyriver.Bean.Building;
import com.whucs.energyriver.Bean.HttpData;
import com.whucs.energyriver.Public.Common;
import retrofit2.http.POST;
import rx.Observable;

import java.util.List;

public class BuildingBiz {
    public interface BuildingService {
        @POST("/build/getBuildingInfo")
        Observable<HttpData<List<Building>>> getBuildingInfo();
    }

    public Observable<HttpData<List<Building>>> getBuildingInfo(Context context){
        return Common.getRetrofit(context).create(BuildingService.class).getBuildingInfo();
    }
}

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
       @GET("branch/getRoomInfo")
       Observable<List<Building>> getBuildingInfo(@Query("userName")String userName,@Query("password")String psw);
    }

    public Observable<List<Building>> getBuildingInfo(Context context){
        return Common.getRetrofit(context).create(BuildingService.class).getBuildingInfo(Common.getUserName(context),Common.getPwd(context));
    }
}

package com.whucs.energyriver.Biz;


import android.content.Context;

import com.whucs.energyriver.Bean.HttpData;
import com.whucs.energyriver.Bean.Percent;
import com.whucs.energyriver.Bean.Rank;
import com.whucs.energyriver.Public.Common;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public class RankBiz {
    interface RankService {
        @GET("energyInfo/getMobileBuildEnergyInfo")
        Observable<HttpData<List<Rank>>> getRoomRank(@Query("userID")Long userID);

        @GET("energyInfo/getMobileBranchEnergyInfo")
        Observable<HttpData<List<Rank>>> getBranchRank(@Query("userID")Long userID);

        @GET("energyInfo/getMobileDeviceEnergyInfo")
        Observable<HttpData<List<Rank>>> getDeviceRank(@Query("userID")Long userID);

         @GET("energyInfo/getMobileEnergyByType")
        Observable<HttpData<List<Percent>>> getMobileEnergyByType(@Query("userID")Long userID);

    }

    public Observable<HttpData<List<Rank>>> getRoomRank(Context context){
        return Common.getRetrofit(context).create(RankService.class).getRoomRank(Common.getID(context));
    }

    public Observable<HttpData<List<Rank>>> getBranchRank(Context context){
        return Common.getRetrofit(context).create(RankService.class).getBranchRank(Common.getID(context));
    }

    public Observable<HttpData<List<Rank>>> getDeviceRank(Context context){
        return Common.getRetrofit(context).create(RankService.class).getDeviceRank(Common.getID(context));
    }

    public Observable<HttpData<List<Percent>>> getMobileEnergyByType(Context context){
        return Common.getRetrofit(context).create(RankService.class).getMobileEnergyByType(Common.getID(context));
    }
}

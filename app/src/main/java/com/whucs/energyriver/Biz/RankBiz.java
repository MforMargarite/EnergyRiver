package com.whucs.energyriver.Biz;


import android.content.Context;

import com.whucs.energyriver.Bean.DeptRank;
import com.whucs.energyriver.Bean.HttpData;
import com.whucs.energyriver.Bean.RoomRank;
import com.whucs.energyriver.Bean.ScoreRank;
import com.whucs.energyriver.Public.Common;

import java.util.List;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public class RankBiz {
    interface RankService {
        @POST("roomrank")
        Observable<HttpData<List<RoomRank>>> getRoomRank();
        @POST("roomrank")
        Observable<HttpData<List<ScoreRank>>> getScoreRank(@Query("username") String username, @Query("password") String password);
        @POST("roomrank")
        Observable<HttpData<List<DeptRank>>> getDeptRank(@Query("username") String username, @Query("password") String password);
    }

    public Observable<HttpData<List<RoomRank>>> getRoomRank(Context context){
        return Common.getRetrofit(context).create(RankService.class).getRoomRank();
    }
}

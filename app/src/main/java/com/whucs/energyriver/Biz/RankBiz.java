package com.whucs.energyriver.Biz;


import com.whucs.energyriver.Bean.DeptRank;
import com.whucs.energyriver.Bean.HttpResult;
import com.whucs.energyriver.Bean.RoomRank;
import com.whucs.energyriver.Bean.ScoreRank;
import com.whucs.energyriver.Bean.User;
import com.whucs.energyriver.Public.Common;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public class RankBiz {
    interface RankService {
        @POST("/roomrank")
        Observable<HttpResult<List<RoomRank>>> getRoomRank();
        @POST("/roomrank")
        Observable<HttpResult<List<ScoreRank>>> getScoreRank(@Query("username") String username, @Query("password") String password);
        @POST("/roomrank")
        Observable<HttpResult<List<DeptRank>>> getDeptRank(@Query("username") String username, @Query("password") String password);
    }

    public Observable<HttpResult<List<RoomRank>>> getRoomRank(){
        return Common.getRetrofit().create(RankService.class).getRoomRank();
    }
}

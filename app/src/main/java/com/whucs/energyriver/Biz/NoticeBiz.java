package com.whucs.energyriver.Biz;


import android.content.Context;
import com.whucs.energyriver.Bean.HttpListData;
import com.whucs.energyriver.Bean.HttpResult;
import com.whucs.energyriver.Bean.Notice;
import com.whucs.energyriver.Public.Common;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


public class NoticeBiz {
    private static NoticeService noticeService ;

    public static NoticeService getNoticeService(Context context){
        if(noticeService == null)
            noticeService = Common.getRetrofit(context).create(NoticeService.class);
        return noticeService;
    }

    interface NoticeService {
        @POST("system/notice/update")
        Observable<HttpResult> updateNotice(@Query("nID") Long nID, @Query("state") int state);

        @GET("system/notice/listByBranchAndTypeUnread")
        Observable<HttpListData<List<Notice>>> getNoticeByType(@Query("userID") Long userID,@Query("type") int type);

        @GET("system/notice/listByBranchAndType")
        Observable<HttpListData<List<Notice>>> getNoticeByTypeAndPage(@Query("userID") Long userID,@Query("type") int type,@Query("pageIndex")int pageIndex,@Query("pageSize")int pageSize);

    }


    public Observable<HttpResult> updateNotice (Context context,Long nID, int state){
        return getNoticeService(context).updateNotice(nID,state);
    }

    public Observable<HttpListData<List<Notice>>> getNoticeByType(Context context, Long userID, int type){
        return getNoticeService(context).getNoticeByType(userID,type+1);
    }

    public Observable<HttpListData<List<Notice>>> getNoticeByTypeAndPage(Context context, Long userID, int type,int pageIndex,int pageSize){
        return getNoticeService(context).getNoticeByTypeAndPage(userID,type+1,pageIndex,pageSize);
    }

}

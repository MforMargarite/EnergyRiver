package com.whucs.energyriver.Biz;


import android.content.Context;

import com.whucs.energyriver.Bean.Bill;
import com.whucs.energyriver.Public.Common;

import java.util.List;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public class BillBiz {
    private static BillService billService;
    public static BillService getBillService(Context context){
        if(billService == null)
            billService = Common.getRetrofit(context).create(BillService.class);
        return billService;
    }

    interface BillService{
        @POST("bill/get")
        Observable<List<Bill>>getBillByUser(@Query("userID")Long userID);
    }

    public Observable<List<Bill>> getBillByUser(Context context,Long userID){
        return getBillService(context).getBillByUser(userID);
    }

}

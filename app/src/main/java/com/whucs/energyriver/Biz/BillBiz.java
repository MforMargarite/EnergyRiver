package com.whucs.energyriver.Biz;


import android.content.Context;

import com.whucs.energyriver.Bean.Bill;
import com.whucs.energyriver.Bean.HttpData;
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
        @POST("bill/listBill")
        Observable<HttpData<List<Bill>>>getBillByUser(@Query("userID")Long userID, @Query("pwd")String pwd);
    }

    public Observable<HttpData<List<Bill>>> getBillByUser(Context context,Long userID,String pwd){
        return getBillService(context).getBillByUser(userID,pwd);
    }

}

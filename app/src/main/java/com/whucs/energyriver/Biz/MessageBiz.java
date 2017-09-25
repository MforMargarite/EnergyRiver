package com.whucs.energyriver.Biz;

import android.content.Context;

import com.whucs.energyriver.Public.Common;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


public class MessageBiz {
    private static MessageService msgService;

    public static MessageService getMsgService(Context context){
        if(msgService == null)
            msgService = Common.getMsgRetrofit(context).create(MessageService.class);
        return msgService;
    }

    interface MessageService {
        @GET("Service.asmx/SendMessageStr")
        Observable<String> getVerifyCode(@Query("Id") int Id, @Query("Name") String name, @Query("Psw") String Psw, @Query("Message") String Message,
                                         @Query("Phone") String Phone, @Query("Timestamp") String Timestamp);
    }

    public Observable<String> getVerifyCode(Context context, int Id, String name, String Psw, String Message, String Phone, String Timestamp){
        return getMsgService(context).getVerifyCode(Id, name, Psw, Message, Phone, Timestamp);
    }
}

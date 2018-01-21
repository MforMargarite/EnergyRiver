package com.whucs.energyriver.Biz;


import android.content.Context;
import com.whucs.energyriver.Bean.VersionInfo;
import com.whucs.energyriver.Interceptor.AddCookieInterceptor;
import com.whucs.energyriver.Public.Common;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public class MainActivityBiz {
    private static MainActivityService mService;
    public static MainActivityService getMainActivityService(Context context){
        if(mService == null)
        {
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            httpClientBuilder.connectTimeout(5, TimeUnit.SECONDS);
            httpClientBuilder.readTimeout(10,TimeUnit.SECONDS);
            httpClientBuilder.writeTimeout(10,TimeUnit.SECONDS);
            httpClientBuilder.addInterceptor(new AddCookieInterceptor(context));
            Retrofit retrofit = new Retrofit.Builder()
                    .client(httpClientBuilder.build())
                    .baseUrl("http://api.fir.im/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            mService = retrofit.create(MainActivityService.class);
        }
        return mService;
    }

    interface MainActivityService{
        @GET("apps/latest/59cc9e00ca87a8027d000125")
        Observable<VersionInfo>getVersionInfo(@Query("api_token") String api_token);
    }

    public Observable<VersionInfo> getVersionInfo(Context context){
        return getMainActivityService(context).getVersionInfo(Common.getFirApiToken());
    }

}

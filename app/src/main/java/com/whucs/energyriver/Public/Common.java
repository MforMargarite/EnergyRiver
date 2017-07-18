package com.whucs.energyriver.Public;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import com.whucs.energyriver.Bean.User;
import com.whucs.energyriver.Interceptor.AddCookieInterceptor;
import com.whucs.energyriver.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Common {
    private static Long id = -1L;
    private static Bitmap avatar = null;
    public static final String ROOT = "http://192.168.137.1:8008/";
    public static final String Inquiry = "view/energyInfo/index.html";
    public static final String[] types = {"照明","空调","插座"};
    public static int[] cate_icon = {R.mipmap.light,R.mipmap.air_condition,R.mipmap.socket};

    public static Bitmap getAvatar() {
        return avatar;
    }

    public static boolean hasAvatar(){
        return avatar != null;
    }

    public static void setAvatar(Bitmap avatar) {
        Common.avatar = avatar;
    }

    private static SharedPreferences sharedPreferences;
    private static Retrofit retrofit;

    public static Long getID(Context context){
        //获得登陆id
        if(sharedPreferences == null)
            sharedPreferences =  context.getSharedPreferences("data",0);
        if(id == -1L)
            id = sharedPreferences.getLong("id",-1L);
        return id;
    }

    public static Retrofit getRetrofit(Context context) {
        if(retrofit == null) {
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            httpClientBuilder.connectTimeout(5, TimeUnit.SECONDS);
            httpClientBuilder.addInterceptor(new AddCookieInterceptor(context));
            retrofit = new Retrofit.Builder()
                    .client(httpClientBuilder.build())
                    .baseUrl(Common.ROOT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public static void setUser(Context context,User user){
        if(sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences("data",0);
        Set<String> cookieSet =  new HashSet<>();
        cookieSet.add("tokenNo="+user.getTokenNo()+";");
        cookieSet.add("userID="+user.getUserID());
        sharedPreferences.edit().putLong("id",user.getUserID())
                .putString("username",user.getUsername())
                .putInt("score",user.getScore())
                .putStringSet("cookies",cookieSet)
                .apply();
    }

    public static void unLogUser(Context context){
        if(sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences("data",0);
        sharedPreferences.edit().remove("id")
                .remove("username")
                .remove("score")
                .remove("cookies")
                .remove("token")
                .apply();
    }

    public static String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".png";
    }

    public static SharedPreferences getSharedPreference(Context context){
        if(sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences("data",0);
        return sharedPreferences;
    }
}

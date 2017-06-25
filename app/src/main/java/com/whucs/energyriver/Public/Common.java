package com.whucs.energyriver.Public;

import android.content.Context;
import android.content.SharedPreferences;
import com.whucs.energyriver.Bean.User;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Common {
    private static int id = -1;
    private static SharedPreferences sharedPreferences;
    private static Retrofit retrofit;

    public static int getID(Context context){
        //获得登陆id
        if(sharedPreferences == null)
            sharedPreferences =  context.getSharedPreferences("data",0);
        if(id == -1)
            id = sharedPreferences.getInt("id",-1);
        return id;
    }

    public static Retrofit getRetrofit() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Params.ROOT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public static void setUser(Context context,User user){
        if(sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences("data",0);
        sharedPreferences.edit().putInt("id",user.getId())
                .putString("username",user.getUsername())
                .putInt("score",user.getScore())
                .apply();
    }

    public static void unlogUser(Context context){
        if(sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences("data",0);
        sharedPreferences.edit().remove("id")
                .remove("username")
                .remove("score")
                .apply();
    }

}

package com.whucs.energyriver.Public;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.HashMap;
import java.util.HashSet;

//处理Cookie和Token
public class HttpUtil {
    //Cookie: HashSet->HashMap<String,String>
    private HashMap<String,String> convertToCookieMap(HashSet<String> cookieSet){
        HashMap<String,String>map = new HashMap<>();
        for (String cookieStr:cookieSet) {
            String[] cookiePart = cookieStr.split("=");
            map.put(cookiePart[0],cookiePart[1]);
        }
        return map;
    }

    public boolean saveToken(Context context,HashSet<String>cookieSet){
        HashMap<String,String>cookieMap = convertToCookieMap(cookieSet);
        if(cookieMap.containsKey("tokenNo")){
            //Log.e("what","tokenNo:"+cookieMap.get("tokenNo"));
            Common.getSharedPreference(context).edit().putString("token",cookieMap.get("token")).apply();
            return true;
        }
        return false;
    }

    public static boolean isNetworkAvailable(Context context){
        boolean netStatus = false;
        try{
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if(networkInfo!=null)
                if(networkInfo.isAvailable() && networkInfo.isConnected())
                    netStatus = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return netStatus;
    }

}

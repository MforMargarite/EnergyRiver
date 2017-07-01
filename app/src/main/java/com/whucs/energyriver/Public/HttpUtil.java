package com.whucs.energyriver.Public;


import android.content.Context;
import android.content.SharedPreferences;

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

    public boolean saveCookieAndToken(Context context,HashSet<String>cookieSet){
        HashMap<String,String>cookieMap = convertToCookieMap(cookieSet);
        SharedPreferences.Editor editor = Common.getSharedPreference(context).edit();
        editor.putStringSet("cookies",cookieSet).apply();
        if(cookieMap.containsKey("tokenNo")){
            editor.putString("token",cookieMap.get("token")).apply();
            return true;
        }
        return false;
    }


}

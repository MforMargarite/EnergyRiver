package com.whucs.energyriver.Interceptor;

import android.content.Context;

import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.Public.HttpUtil;
import java.io.IOException;
import java.util.HashSet;
import okhttp3.Interceptor;
import okhttp3.Response;


public class ReceiveCookieInterceptor implements Interceptor{
    Context context;
    public ReceiveCookieInterceptor(Context context){
        this.context = context;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (Common.getSharedPreference(context).getStringSet("cookies", null) == null) {
            if (!response.headers("Set-Cookie").isEmpty()) {
                HashSet<String> cookies = new HashSet<>();
                for (String header : response.headers("Cookie")) {
                    cookies.add(header);
                }
                new HttpUtil().saveCookieAndToken(context, cookies);//将Cookies和token存入SharedPreference
            }
        }
        return response;
    }
}

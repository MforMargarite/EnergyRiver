package com.whucs.energyriver.Interceptor;

import android.content.Context;
import android.util.Log;

import com.whucs.energyriver.Public.Common;
import java.io.IOException;
import java.util.Set;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class AddCookieInterceptor implements Interceptor{
    Context context;
    public AddCookieInterceptor(Context context){
        this.context = context;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        Set<String> cookieSet = Common.getSharedPreference(context).getStringSet("cookies",null);
        if(cookieSet!=null) {
            StringBuilder stringBuilder = new StringBuilder();
            int index = 0;
            for (String cookie : cookieSet) {
                if(index != cookieSet.size()-1)
                    stringBuilder.append(cookie+";");
                else
                    stringBuilder.append(cookie);
                index++;
            }
            Log.e("what",stringBuilder.toString());
            builder.addHeader("Cookie", stringBuilder.toString());
        }
        return chain.proceed(builder.build());
    }
}

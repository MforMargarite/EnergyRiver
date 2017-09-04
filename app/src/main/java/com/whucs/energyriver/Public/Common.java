package com.whucs.energyriver.Public;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.whucs.energyriver.Bean.SubUser;
import com.whucs.energyriver.Bean.User;
import com.whucs.energyriver.Interceptor.AddCookieInterceptor;
import com.whucs.energyriver.R;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
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
    private static Long branchID = -1L;
    private static Bitmap avatar = null;
    public static final String ROOT = "http://106.15.35.178:8008/EnergyRiver/";
    public static final String BILL = "view/bill/info.html?id=";
  //  public static final String ROOT = "http://192.168.137.1:8008/";
    public static final String Inquiry = "view/energyInfo/index.html";
    public static final String[] types = {"照明","空调","插座"};
    public static int[] cate_icon = {R.mipmap.light,R.mipmap.air_condition,R.mipmap.socket};
    private static SharedPreferences sharedPreferences;
    private static Retrofit retrofit;
    public static final String[] loops = {"{\"loopID\":1,\"loopTypeID\":1,\"loopName\":\"照明回路01\",\"openStatus\":false}",
            "{\"loopID\":2,\"loopTypeID\":1,\"loopName\":\"照明回路02\",\"openStatus\":false}",
            "{\"loopID\":3,\"loopTypeID\":2,\"loopName\":\"空调回路01\",\"openStatus\":false}",
            "{\"loopID\":4,\"loopTypeID\":3,\"loopName\":\"插座回路01\",\"openStatus\":true}",
            "{\"loopID\":5,\"loopTypeID\":3,\"loopName\":\"插座回路02\",\"openStatus\":false}",
            "{\"loopID\":6,\"loopTypeID\":3,\"loopName\":\"插座回路03\",\"openStatus\":true}"};

    public static Bitmap getAvatar(Context context) {
        if(sharedPreferences == null)
            sharedPreferences =  context.getSharedPreferences("data",0);
        if(sharedPreferences.contains("avatar")) {
            byte[] avatar_data = Base64.decode(sharedPreferences.getString("avatar", "").getBytes(),Base64.DEFAULT);
            avatar = BitmapFactory.decodeByteArray(avatar_data,0,avatar_data.length);
        }
        return avatar;
    }

    public static boolean hasAvatar(Context context){
        if(sharedPreferences == null)
            sharedPreferences =  context.getSharedPreferences("data",0);
        return sharedPreferences.contains("avatar");
    }

    public static boolean hasAuth(Context context){
        boolean isAuth;
        if(sharedPreferences == null)
            sharedPreferences =  context.getSharedPreferences("data",0);
        isAuth = sharedPreferences.getBoolean("isVIP",false);//VIP用户
        if(!isAuth)
            isAuth = sharedPreferences.getBoolean("isSubVIP",false);//子用户时VIP用户的判断
        return isAuth;
    }

    public static void saveAvatar(Context context, Bitmap avatar) {
        //将头像存至数据库
        Common.avatar = avatar;
        if(sharedPreferences == null)
            sharedPreferences =  context.getSharedPreferences("data",0);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        avatar.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        sharedPreferences.edit().putString("avatar",Base64.encodeToString(outputStream.toByteArray(),Base64.DEFAULT)).apply();
    }

    public static String getUserName(Context context){
        if(sharedPreferences == null)
            sharedPreferences =  context.getSharedPreferences("data",0);
        return sharedPreferences.getString("username","");
    }

    public static Integer getScore(Context context){
        if(sharedPreferences == null)
            sharedPreferences =  context.getSharedPreferences("data",0);
        return sharedPreferences.getInt("score",0);
    }

    public static void saveUserName(Context context,String username){
        if(sharedPreferences == null)
            sharedPreferences =  context.getSharedPreferences("data",0);
        sharedPreferences.edit().putString("username",username).apply();
    }


    public static Long getID(Context context){
        //获得登陆id
        if(sharedPreferences == null)
            sharedPreferences =  context.getSharedPreferences("data",0);
        if(id == -1L)
            id = sharedPreferences.getLong("id",-1L);
        return id;
    }

    public static Long getBranchID(Context context){
        //获得对应机构id
        if(sharedPreferences == null)
            sharedPreferences =  context.getSharedPreferences("data",0);
        if(branchID == -1L)
            branchID = sharedPreferences.getLong("branchID",-1L);
        return branchID;
    }

    public static Retrofit getRetrofit(Context context) {
        if(retrofit == null) {
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            httpClientBuilder.connectTimeout(5, TimeUnit.SECONDS);
            httpClientBuilder.readTimeout(10,TimeUnit.SECONDS);
            httpClientBuilder.writeTimeout(10,TimeUnit.SECONDS);
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


    public static void setUser(Context context,User user)throws Exception{
        if(sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences("data",0);
        Set<String> cookieSet =  new HashSet<>();
        cookieSet.add("tokenNo="+user.getData().getTokenNo()+";");
        cookieSet.add("userID="+user.getData().getUserID());
        byte[] avatar_data;
        /*if(avatar == null && user.getHeadImg()!=null){
            String path = Common.ROOT+user.getHeadImg().split("../../")[1];
            avatar_data = getAvatarByUrl(path);
            sharedPreferences.edit().putString("avatar",Base64.encodeToString(avatar_data,Base64.DEFAULT)).apply();
            //bitmap->byte[]->base64加密->String 保存头像至数据库
        }*/
        sharedPreferences.edit().putLong("id",user.getData().getUserID())
                .putLong("branchID",user.getBranchID())
                .putString("username",user.getData().getUsername())
                .putInt("score",user.getUserPoint())
                .putBoolean("isAdmin",(user.getIsAdmin()>0))
                .putBoolean("isVIP",(user.getIsVIP()>0))
                .putStringSet("cookies",cookieSet)
                .apply();
    }

    public static void setSubUser(Context context,SubUser user)throws Exception{
        if(sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences("data",0);
        byte[] avatar_data;
        if(avatar == null && user.getHeadImg()!=null){
            String path = Common.ROOT+user.getHeadImg().split("../../")[1];
            avatar_data = getAvatarByUrl(path);
            sharedPreferences.edit().putString("avatar",Base64.encodeToString(avatar_data,Base64.DEFAULT)).apply();
            //bitmap->byte[]->base64加密->String 保存头像至数据库
        }
        sharedPreferences.edit().putLong("id",user.getUserID())
                .putLong("buildingID",user.getBuildingID())
                .putString("username",user.getUserName())
                .putBoolean("isSubVIP",true)
                .putLong("creatorID",user.getCreatorID())
                .apply();
    }

    private static byte[] getAvatarByUrl(String path)throws Exception{
        URL url = new URL(path);
        InputStream is = url.openStream();
        avatar = BitmapFactory.decodeStream(is);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] temp = new byte[1024];//缓冲byte数组
        int len;
        while((len = is.read(temp))>-1){
            outputStream.write(temp,0,len);
        }
        outputStream.close();
        is.close();
        return outputStream.toByteArray();
    }

    public static void unLogUser(Context context){
        if(sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences("data",0);
        sharedPreferences.edit().remove("id")
                .remove("username")
                .remove("score")
                .remove("cookies")
                .remove("token")
                .remove("avatar")
                .remove("branchID")
                .remove("isAdmin")
                .remove("isVIP")
                .remove("buildingName")
                .remove("buildingID")
                .remove("isSubVIP")
                .remove("creatorID")
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

    public static void saveBuilding(Context context,Long buildingID,String buildingName){
        if(sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences("data",0);
        sharedPreferences.edit().putString("buildingName",buildingName)
                .putLong("buildingID",buildingID)
                .apply();
    }


}

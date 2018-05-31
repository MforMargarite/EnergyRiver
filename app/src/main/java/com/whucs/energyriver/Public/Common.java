package com.whucs.energyriver.Public;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;

import com.java4less.rchart.gc.ChartColor;
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
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Common {
    private static Long id = -1L;
    private static String pwd = null;
    private static Long branchID = -1L;
    private static Bitmap avatar = null;
    public static int screen_width = 0;
    public static int screen_height = 0;
    private static int MSG_ID = 332;
    private static String MSG_NAME = "yanba";
    private static String MSG_PWD = "123456";
    private static int msg_random;
    public static int times = 0;
    public static String phone_input;

    private static SharedPreferences sharedPreferences;
    private static Retrofit retrofit,msgRetrofit;
    public static final String[] loops = {"{\"loopID\":1,\"loopName\":\"照明回路01\",\"openStatus\":false}",
            "{\"loopID\":2,\"loopName\":\"照明回路02\",\"openStatus\":false}",
            "{\"loopID\":3,\"loopName\":\"空调回路01\",\"openStatus\":false}",
            "{\"loopID\":4,\"loopName\":\"插座回路01\",\"openStatus\":true}",
            "{\"loopID\":5,\"loopName\":\"插座回路02\",\"openStatus\":false}",
            "{\"loopID\":6,\"loopName\":\"插座回路03\",\"openStatus\":true}"};

    public static final String ROOT = "http://106.15.35.178:8008/EnergyRiver/";
    public static final String BILL = "view/bill/bill_details_mobile.html";               //能耗账单详情
  //  public static final String ROOT = "http://192.168.137.1:8008/";
    public static final String Inquiry = "view/apphome/index.html";            //首页-能耗概况
    public static final String LANDSCAPE = "view/apphome/landscape.html";      //首页-横屏
    public static final String MESSAGE_VERIFY="http://124.172.234.157:8180/";//短信验证码

    public static final String[] types = {"照明","空调","插座","总回路","","","其他"};
    public static int[] cate_icon = {R.mipmap.light,R.mipmap.air_condition,R.mipmap.socket};
    public static String[] type_colors={ChartColor.ORANGE,ChartColor.YELLOW,ChartColor.BEIGE};
    public static final String[] noticeType = {"用电安全","电能参数","环境安全"};

    private static boolean checkUpdate;
    public static String latest_version;
    private static String fir_api_token = "cea43c4fc84e1414f7747711b0d7f5a7";


    public static String getFirApiToken(){
        return fir_api_token;
    }

    public static String getLatestVersion(){
        return latest_version;
    }

    public static String getLatestUrl(){
        return ROOT+"Apk/EnergyRiver_"+latest_version+".apk";
    }

    public static Bitmap getAvatar(Context context) {
        if(sharedPreferences == null)
            sharedPreferences =  context.getSharedPreferences("data",0);
        if(sharedPreferences.contains("avatar")) {
            byte[] avatar_data = Base64.decode(sharedPreferences.getString("avatar", "").getBytes(),Base64.DEFAULT);
            avatar = BitmapFactory.decodeByteArray(avatar_data,0,avatar_data.length);
        }
        return avatar;
    }

    public static Bitmap getLastAvatar(Context context) {
        if(sharedPreferences == null)
            sharedPreferences =  context.getSharedPreferences("data",0);
        if(sharedPreferences.contains("lastAvatar")) {
            byte[] avatar_data = Base64.decode(sharedPreferences.getString("lastAvatar", "").getBytes(),Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(avatar_data,0,avatar_data.length);
        }
        return null;
    }

    public static boolean hasAvatar(Context context){
        if(sharedPreferences == null)
            sharedPreferences =  context.getSharedPreferences("data",0);
        return sharedPreferences.contains("avatar");
    }

    public static boolean hasLastAvatar(Context context){
        if(sharedPreferences == null)
            sharedPreferences =  context.getSharedPreferences("data",0);
        return sharedPreferences.contains("lastAvatar");
    }


    public static boolean hasAuth(Context context){
        boolean isAuth;
        if(sharedPreferences == null)
            sharedPreferences =  context.getSharedPreferences("data",0);
        isAuth = sharedPreferences.getBoolean("isVIP",false);//VIP用户
        if(!isAuth)
            isAuth = sharedPreferences.getBoolean("isSubVIP",false);//子用户时VIP用户的判断
        isAuth = true;
        return isAuth;
    }

    public static void saveAvatar(Context context, Bitmap avatar) {
        //将头像存至数据库
        Common.avatar = avatar;
        if(sharedPreferences == null)
            sharedPreferences =  context.getSharedPreferences("data",0);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        avatar.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        String avatarStr = Base64.encodeToString(outputStream.toByteArray(),Base64.DEFAULT);
        sharedPreferences.edit().putString("avatar",avatarStr)
                .putString("lastAvatar",avatarStr)
                .apply();
    }

    public static String getUserName(Context context){
        if(sharedPreferences == null)
            sharedPreferences =  context.getSharedPreferences("data",0);
        return sharedPreferences.getString("username","");
    }

    public static String getMobile(Context context){
        if(sharedPreferences == null)
            sharedPreferences =  context.getSharedPreferences("data",0);
        return sharedPreferences.getString("mobile","");
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

    public static void saveMobile(Context context,String mobile){
        if(sharedPreferences == null)
            sharedPreferences =  context.getSharedPreferences("data",0);
        sharedPreferences.edit().putString("mobile",mobile).apply();
    }

    public static Long getID(Context context){
        //获得登陆id
        if(sharedPreferences == null)
            sharedPreferences =  context.getSharedPreferences("data",0);
        if(id == -1L)
            id = sharedPreferences.getLong("id",-1L);
        return id;
    }

    public static String getPwd(Context context){
        //获得密码
        if(sharedPreferences == null)
            sharedPreferences =  context.getSharedPreferences("data",0);
        if(pwd == null)
            pwd = sharedPreferences.getString("pwd","");
        return pwd;
    }

    public static Long getBranchID(Context context){
        //获得对应机构id
        if(sharedPreferences == null)
            sharedPreferences =  context.getSharedPreferences("data",0);
        if(branchID == -1L)
            branchID = sharedPreferences.getLong("branchID",-1L);
        return branchID;
    }

    public static Retrofit getMsgRetrofit(Context context) {
        if(msgRetrofit == null) {
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            httpClientBuilder.connectTimeout(5, TimeUnit.SECONDS);
            httpClientBuilder.readTimeout(10,TimeUnit.SECONDS);
            httpClientBuilder.writeTimeout(10,TimeUnit.SECONDS);
            httpClientBuilder.addInterceptor(new AddCookieInterceptor(context));
            msgRetrofit = new Retrofit.Builder()
                    .client(httpClientBuilder.build())
                    .baseUrl(Common.MESSAGE_VERIFY)
                    .addConverterFactory(ScalarsConverterFactory.create())//以String类型解释结果
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return msgRetrofit;
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


    public static void setUser(Context context,User user,String pwd)throws Exception{
        if(sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences("data",0);
        Set<String> cookieSet =  new HashSet<>();
        cookieSet.add("tokenNo="+user.getData().getTokenNo()+";");
        cookieSet.add("userID="+user.getData().getUserID());
        sharedPreferences.edit().remove("lastAvatar").apply();
        if(avatar == null && user.getHeadImg()!=null && !user.getHeadImg().trim().equals("")){
            String path = Common.ROOT+user.getHeadImg().split("../../")[1];
            getAvatarByUrl(context,path);
            //bitmap->byte[]->base64加密->String 保存头像至数据库
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            avatar.compress(Bitmap.CompressFormat.PNG,100,outputStream);
            String avatarStr = Base64.encodeToString(outputStream.toByteArray(),Base64.DEFAULT);
            sharedPreferences.edit().putString("avatar",avatarStr)
                    .putString("lastAvatar",avatarStr)
                    .apply();
        }

        sharedPreferences.edit().putLong("id",user.getData().getUserID())
                .putString("pwd",pwd)
                .putString("lastUserName",user.getData().getUsername())
                .putString("mobile",user.getTelephone())
                .putLong("branchID",user.getBranchID())
                .putString("username",user.getData().getUsername())
                .putInt("score",user.getUserPoint())
                .putBoolean("isAdmin",(user.getIsAdmin()>0))
                //todo 发现后台传回的数据中没有isVIP 需检查逻辑
                //.putBoolean("isVIP",(user.getIsVIP()>0))
                .putStringSet("cookies",cookieSet)
                .apply();
    }

    public static void setSubUser(Context context,SubUser user)throws Exception{
        if(sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences("data",0);
        if(avatar == null && user.getHeadImg()!=null){
            String path = Common.ROOT+user.getHeadImg().split("../../")[1];
            getAvatarByUrl(context,path);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            avatar.compress(Bitmap.CompressFormat.PNG,100,outputStream);
            sharedPreferences.edit().putString("avatar",Base64.encodeToString(outputStream.toByteArray(),Base64.DEFAULT)).apply();
            //bitmap->byte[]->base64加密->String 保存头像至数据库
        }
        sharedPreferences.edit().putLong("id",user.getUserID())
                .putLong("subBuildingID",user.getBuildingID())
                .putString("username",user.getUserName())
                .putBoolean("isSubVIP",true)
                .putLong("creatorID",user.getCreatorID())
                .apply();
    }

    private static void getAvatarByUrl(final Context context,final String path){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    InputStream is = url.openStream();
                    avatar = BitmapFactory.decodeStream(is);

               }catch (Exception e){
                    e.printStackTrace();
                    avatar = BitmapFactory.decodeResource(context.getResources(),R.mipmap.avatar);
                }
            }
        });
        thread.start();
        while(true)
            if(!thread.isAlive())
                break;
    }

    public static void unLogUser(Context context){
        if(sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences("data",0);
        avatar = null;id = -1L;pwd = null;branchID = -1L;
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
                .remove("subBuildingID")
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

    public static int getParentWidth(Activity activity){
        if(screen_width == 0) {
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            screen_width = dm.widthPixels;
          }
        return screen_width;
    }

    public static int getParentHeight(Activity activity){
        if(screen_height == 0) {
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            screen_height = dm.heightPixels;
        }
        return screen_height;
    }

    public static void saveBuilding(Context context,Long buildingID,String buildingName){
        if(sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences("data",0);
        sharedPreferences.edit().putString("buildingName",buildingName)
                .putLong("buildingID",buildingID)
                .apply();
    }

    public static String getMsgName(){
        return MSG_NAME;
    }

    public static int getMsgId(){
        return MSG_ID;
    }

    public static String getMsgPwd(){
        return MSG_PWD;
    }

    public static String getTimestamp(){
        return System.currentTimeMillis()/1000L+"";
    }

    public static String getMessage(){
        double random_num = Math.random();
        while(random_num<0.1)
            random_num = Math.random();
        msg_random = (int)(1000000*random_num);
        StringBuilder builder= new StringBuilder();
        builder.append("【盐巴科技】PNP您好，您正在进行手机验证，本次验证码为")
        .append(msg_random)
        .append("，请按页面提示提交验证码，以完成操作(有效时间5分钟)。如非本人操作，请忽略本短信。");
        return builder.toString();
    }

    public static boolean verifySMS(String verify_code){
        int verify = Integer.parseInt(verify_code);
        return verify == msg_random;
    }

    public static boolean getCheckUpdate(Context context){
        if (sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences("data", 0);
        Long curTime = System.currentTimeMillis();
        Long lastCheckTime = sharedPreferences.getLong("last_check_time",curTime);
        if(curTime.equals(lastCheckTime) || curTime - lastCheckTime > 3600000*24)//超过一天
            checkUpdate = true;
        return checkUpdate;
    }

    public static void setCheckUpdate(Context context){
        //进行版本更新检查后 置checkUpdate = false 并更新时间
        if (sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences("data", 0);
        sharedPreferences.edit().putLong("last_check_time",System.currentTimeMillis()).apply();
        checkUpdate = false;
    }



}

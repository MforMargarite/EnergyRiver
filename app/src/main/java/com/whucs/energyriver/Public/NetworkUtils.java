package com.whucs.energyriver.Public;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Looper;
import android.telephony.TelephonyManager;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NetworkUtils {
    ConnectivityManager mConnectivity;
    TelephonyManager mTelephony;
    NetworkInfo info;
    Context context;

    public NetworkUtils(Context context){
        mConnectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        mTelephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        info = mConnectivity.getActiveNetworkInfo();
        this.context = context;
    }


    public boolean isNetworkAvailable(){
        boolean netStatus = false;
        try{
            if(info!=null)
                if(info.isAvailable() && info.isConnected())
                    netStatus = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return netStatus;
    }



}

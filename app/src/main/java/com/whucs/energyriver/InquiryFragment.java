package com.whucs.energyriver;


import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.whucs.energyriver.Bean.HttpListData;
import com.whucs.energyriver.Bean.Notice;
import com.whucs.energyriver.Biz.NoticeBiz;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.Public.NetworkUtils;
import com.whucs.energyriver.Widget.StateSwitchFragment;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class InquiryFragment extends StateSwitchFragment implements View.OnTouchListener{
    private WebView webView;
    private MainActivity activity;
    private View view;
    private boolean isError = false;

    //下拉刷新参数
    private float startY;
    private boolean isShowing = false;
    private boolean pullToRefresh = false;
    private WebViewClient webClient;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(activity == null)
            activity = (MainActivity) getActivity();
        webClient = new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                activity.hidePullToRefresh();
                activity.setRefreshState("");
                pullToRefresh = false;
                if(!isError)
                    showViewByTag("content");
            }
        };
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        //int orientation = getResources().getConfiguration().orientation;
        if(view == null) {
            view = LayoutInflater.from(activity).inflate(R.layout.inquiry, null);
        }
        initWidget(view);
        if(new NetworkUtils(activity).isNetworkAvailable())
            webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
            //todo 修改html的cache-control字段   cache-control:max-age=7200 两小时过期
        else
            webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 开启DOM storage API 功能
        webView.getSettings().setDomStorageEnabled(true);
        // 开启database storage API功能
        webView.getSettings().setDatabaseEnabled(true);
        webView.loadUrl(getInquiryURL());
        /*
        if(orientation == Configuration.ORIENTATION_PORTRAIT)
            webView.loadUrl(getInquiryURL());
        else
            webView.loadUrl(getLandscapeURL());*/
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(activity == null)
            activity = (MainActivity) getActivity();
        if(isVisibleToUser)
            getNoticeUnReadNum();

    }



    private void getNoticeUnReadNum(){
        NoticeBiz noticeBiz = new NoticeBiz();
        noticeBiz.getNoticeByType(activity,Common.getID(activity),-2)
                .subscribeOn(Schedulers.io())
                .map(new Func1<HttpListData<List<Notice>>, List<Notice>>() {
                    @Override
                    public List<Notice> call(HttpListData<List<Notice>> noticeHttpListData) {
                        return noticeHttpListData.getData();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Notice>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("what",e.getMessage());

                    }

                    @Override
                    public void onNext(List<Notice> notices) {
                        activity.setMenuNum(notices.size());
                    }
                });
    }

    private void initWidget(View view){
        iniAdapter(view);
        webView = (WebView) view.findViewById(R.id.result);
        webView.setOnTouchListener(this);
        webView.getSettings().setJavaScriptEnabled(true);//允许js交互
        webView.setWebViewClient(webClient);
    }

    @Override
    public void reload() {
        showViewByTag("loading");
        isError = false;//初始化本次加载结果

        //还存在问题
        if(new NetworkUtils(activity).isNetworkAvailable())
            webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        else
            webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ONLY);

        //if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            webView.loadUrl(getInquiryURL());
       /* else
            webView.loadUrl(getLandscapeURL());*/
    }

    private String getInquiryURL(){
        String url = Common.ROOT + Common.Inquiry;
        SharedPreferences sharedPreferences = Common.getSharedPreference(activity);
        if(sharedPreferences.getLong("subBuildingID",-1L) != -1L )
            url += "?buildingID="+sharedPreferences.getLong("subBuildingID",-1L);
        else
            url += "?userID="+sharedPreferences.getLong("id",-1L);
        return url;
    }

    private String getLandscapeURL(){
        String url = Common.ROOT + Common.LANDSCAPE;
        SharedPreferences sharedPreferences = Common.getSharedPreference(activity);
        if(sharedPreferences.getLong("subBuildingID",-1L) != -1L )
            url += "?buildingID="+sharedPreferences.getLong("subBuildingID",-1L);
        else
            url += "?userID="+sharedPreferences.getLong("id",-1L);
        return url;
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch(motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = motionEvent.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if(webView.getScrollY() == 0) {//已经滑动到顶部
                    float dist = motionEvent.getY() - startY;
                    if (dist > 20 && dist <= 220) {
                        isShowing = true;
                        activity.showPullToRefresh((int) dist);
                        return true;
                    } else if (dist > 220) {
                        activity.showPullToRefresh(216);
                        pullToRefresh = true;
                        return true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if(pullToRefresh) {
                    webView.loadUrl(getInquiryURL());
                    activity.setRefreshState("正在刷新");
                    return true;
                }else if(isShowing) {
                    activity.hidePullToRefresh();
                    isShowing = false;
                }
                break;
        }
        return false;
    }

}

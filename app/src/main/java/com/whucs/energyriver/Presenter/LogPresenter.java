package com.whucs.energyriver.Presenter;


import android.content.Context;
import android.util.Log;

import com.whucs.energyriver.Bean.SubUser;
import com.whucs.energyriver.Bean.User;
import com.whucs.energyriver.Biz.UserBiz;
import com.whucs.energyriver.Public.HttpUtil;
import com.whucs.energyriver.View.LogView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class LogPresenter {
    private LogView logView;
    private UserBiz userBiz;

    public LogPresenter(LogView logView){
        this.logView = logView;
        this.userBiz = new UserBiz();
    }

    public void login(final Context context){
        logView.showWaiting();
        userBiz.login(context,logView.getUsername(),logView.getPassword())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {
                        logView.hideWaiting();
                    }

                    @Override
                    public void onError(Throwable e) {
                        String msg;
                        if(HttpUtil.isNetworkAvailable(context))
                            msg = "用户名或密码错误,请重试";
                        else
                            msg = "网络不可用,请连接网络后重试";
                        logView.loginError(msg);
                        logView.hideWaiting();
                    }

                    @Override
                    public void onNext(User user) {
                        logView.setUser(user);

                    }
                });
    }

    public void subLogin(final Context context){
        logView.showWaiting();
        userBiz.subLogin(context,logView.getUsername(),logView.getPassword())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SubUser>() {
                    @Override
                    public void onCompleted() {
                        logView.hideWaiting();
                    }

                    @Override
                    public void onError(Throwable e) {
                        //  Log.e("what",e.getMessage());
                        String msg;
                        if(HttpUtil.isNetworkAvailable(context))
                            msg = "用户名或密码错误,请重试";
                        else
                            msg = "网络不可用,请连接网络后重试";
                        logView.loginError(msg);
                        logView.hideWaiting();
                    }

                    @Override
                    public void onNext(SubUser user) {
                        logView.setSubUser(user);

                    }
                });
    }
}

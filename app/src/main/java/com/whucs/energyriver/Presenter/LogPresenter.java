package com.whucs.energyriver.Presenter;


import android.content.Context;

import com.whucs.energyriver.Bean.HttpData;
import com.whucs.energyriver.Bean.User;
import com.whucs.energyriver.Biz.LogBiz;
import com.whucs.energyriver.View.LogView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class LogPresenter {
    private LogView logView;
    private LogBiz logBiz;

    public LogPresenter(LogView logView){
        this.logView = logView;
        this.logBiz = new LogBiz();
    }

    public void login(Context context){
        logView.showWaiting();
        logBiz.login(context,logView.getUsername(),logView.getPassword())
                .subscribeOn(Schedulers.io())
                .map(new Func1<HttpData<User>, User>() {
                    @Override
                    public User call(HttpData<User> userHttpData) {
                        return userHttpData.getData();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {
                        logView.hideWaiting();
                    }

                    @Override
                    public void onError(Throwable e) {
                        logView.loginError(e.getMessage());
                        logView.hideWaiting();
                    }

                    @Override
                    public void onNext(User user) {
                        logView.setUser(user);
                    }
                });
    }
}

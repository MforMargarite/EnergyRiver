package com.whucs.energyriver.Presenter;


import android.content.Context;

import com.whucs.energyriver.Bean.User;
import com.whucs.energyriver.Biz.UserBiz;
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

    public void login(Context context){
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

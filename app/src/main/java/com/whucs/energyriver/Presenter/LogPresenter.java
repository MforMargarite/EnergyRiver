package com.whucs.energyriver.Presenter;


import com.whucs.energyriver.Bean.User;
import com.whucs.energyriver.Biz.LogBiz;
import com.whucs.energyriver.View.LogView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class LogPresenter {
    private LogView logView;
    private LogBiz logBiz;

    public LogPresenter(LogView logView){
        this.logView = logView;
        this.logBiz = new LogBiz();
    }

    public void login(){
        logView.showWaiting();
        logBiz.login(logView.getUsername(),logView.getPassword())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        logView.loginError();
                        logView.hideWaiting();
                    }

                    @Override
                    public void onNext(User user) {
                        logView.setUser(user);
                    }
                });
    }
}

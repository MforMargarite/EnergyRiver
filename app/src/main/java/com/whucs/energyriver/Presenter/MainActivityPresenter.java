package com.whucs.energyriver.Presenter;


import android.content.Context;
import android.util.Log;

import com.whucs.energyriver.Bean.VersionInfo;
import com.whucs.energyriver.Biz.MainActivityBiz;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.View.MainActivityView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivityPresenter {
    private MainActivityView MainActivityView;
    private MainActivityBiz MainActivityBiz;

    public MainActivityPresenter(MainActivityView MainActivityView){
        this.MainActivityView = MainActivityView;
        this.MainActivityBiz = new MainActivityBiz();
    }

    public void getVersionInfo(Context context){
        MainActivityBiz.getVersionInfo(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VersionInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("what",e.getMessage());
                    }

                    @Override
                    public void onNext(VersionInfo info) {
                        MainActivityView.getVersionInfo(info);
                    }
                });
    }
}

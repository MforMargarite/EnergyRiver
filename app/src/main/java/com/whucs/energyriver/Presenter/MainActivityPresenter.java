package com.whucs.energyriver.Presenter;


import android.content.Context;
import android.util.Log;

import com.whucs.energyriver.Bean.Building;
import com.whucs.energyriver.Bean.Tree;
import com.whucs.energyriver.Bean.VersionInfo;
import com.whucs.energyriver.Biz.BuildingBiz;
import com.whucs.energyriver.Biz.MainActivityBiz;
import com.whucs.energyriver.View.MainActivityView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivityPresenter {
    private MainActivityView mainActivityView;
    private MainActivityBiz mainActivityBiz;
    private BuildingBiz buildingBiz;

    public MainActivityPresenter(MainActivityView MainActivityView){
        this.mainActivityView = MainActivityView;
        this.mainActivityBiz = new MainActivityBiz();
        this.buildingBiz = new BuildingBiz();
    }

    public void getVersionInfo(Context context){
        mainActivityBiz.getVersionInfo(context)
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
                        mainActivityView.getVersionInfo(info);
                    }
                });
    }



    public void getBuildingInfo(Context context){
        buildingBiz.getBuildingInfo(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Building>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("what",e.getMessage());
                        mainActivityView.execError("获取房间信息失败");
                    }

                    @Override
                    public void onNext(List<Building> buildings) {
                        mainActivityView.setBuildingInfo(buildings);
                    }
                });
    }
}

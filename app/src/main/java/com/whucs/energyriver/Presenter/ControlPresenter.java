package com.whucs.energyriver.Presenter;

import android.content.Context;
import com.whucs.energyriver.Bean.Building;
import com.whucs.energyriver.Bean.HttpResult;
import com.whucs.energyriver.Bean.Loop;
import com.whucs.energyriver.Biz.BuildingBiz;
import com.whucs.energyriver.Biz.LoopBiz;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.Public.TreeUtil;
import com.whucs.energyriver.View.ControlView;
import java.util.List;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class ControlPresenter {
    private ControlView controlView;
    private LoopBiz loopBiz;
    private BuildingBiz buildingBiz;

    public ControlPresenter(ControlView controlView){
        this.controlView = controlView;
        this.loopBiz = new LoopBiz();
        this.buildingBiz = new BuildingBiz();
    }

    public void getLoopInfoByBuildID(Context context){
        controlView.showWaiting();
        loopBiz.getLoopInfoByBuildID(context,controlView.getBuildingID())
                .map(new Func1<HttpResult<List<Loop>>, List<Loop>>() {
                    @Override
                    public List<Loop> call(HttpResult<List<Loop>> listHttpResult) {
                        return listHttpResult.getData();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Loop>>() {
                    @Override
                    public void onCompleted() {
                        controlView.hideWaiting();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        controlView.execError(e.getMessage());
                        controlView.hideWaiting();
                    }

                    @Override
                    public void onNext(List<Loop> loops) {
                        controlView.setLoopList(loops);
                    }
                });
    }

    public void getFirstBuildUnit(Context context){
        controlView.showWaiting();
        buildingBiz.getBuildingInfo(context,Common.getID(context))
                .map(new Func1<HttpResult<List<Building>>, List<Building>>() {
                    @Override
                    public List<Building> call(HttpResult<List<Building>> listHttpResult) {
                        return listHttpResult.getData();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Building>>() {
                @Override
                public void onCompleted() {
                    controlView.hideWaiting();
                }

                @Override
                public void onError(Throwable e) {
                    controlView.execError(e.getMessage());
                    controlView.hideWaiting();
                }

                @Override
                public void onNext(List<Building> buildings) {
                    Building building = TreeUtil.getFirstChildID(buildings);
                    controlView.setBuildingUnit(building);
                }
            });
    }


}
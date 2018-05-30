package com.whucs.energyriver.Presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.whucs.energyriver.Bean.ACCollect;
import com.whucs.energyriver.Bean.Building;
import com.whucs.energyriver.Bean.HttpListData;
import com.whucs.energyriver.Bean.HttpResult;
import com.whucs.energyriver.Bean.Loop;
import com.whucs.energyriver.Bean.LoopStatus;
import com.whucs.energyriver.Bean.Tree;
import com.whucs.energyriver.Biz.BuildingBiz;
import com.whucs.energyriver.Biz.LoopBiz;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.Public.TreeUtil;
import com.whucs.energyriver.View.ControlView;
import java.util.HashMap;
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

    //根据房间获取回路
    /*public void getLoopInfoByBuildID(Context context){
        controlView.showLoading();
        loopBiz.getLoopInfoByBuildID(context,controlView.getBuildingID())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Loop>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        controlView.execError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Loop> loops) {
                        controlView.setLoopList(loops);
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
                        controlView.execError("获取房间信息失败");
                    }

                    @Override
                    public void onNext(List<Building> buildings) {
                        controlView.setBuildingInfo(buildings);
                    }
                });
    }


    public void getFirstBuildUnit(final Context context){
        controlView.showLoading();
        buildingBiz.getBuildingInfo(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Building>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    controlView.execError(e.getMessage());
                }

                @Override
                public void onNext(List<Building> buildings) {
                    *//*Building building = TreeUtil.getFirstChildID(buildings);
                    Tree tree = new Tree(buildings);
                    building.setBuildingName(tree.getBuildingPath(building,3));*//*
                    controlView.setBuildingUnit(buildings.get(0));
                }
            });
    }*/

    //根据用户获取对应回路
    public void getLoopByUser(Context context){
        controlView.showLoading();
        loopBiz.getLoopByUser(context, Common.getID(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<LoopStatus>>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        controlView.execError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<LoopStatus> loops) {
                        controlView.setAllLoopList(loops);
                    }
                });
    }

    public void updateLoopState(final View view,Context context){
        controlView.showWaiting();
        loopBiz.updateLoop(context,controlView.getLoopID(),controlView.getLoopOpenStatus())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {
                        controlView.hideWaiting();
                    }

                    @Override
                    public void onError(Throwable e) {
                        controlView.hideWaiting();
                        controlView.updateError(view);
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        Log.e("what","update:"+controlView.getLoopID()+" "+controlView.getLoopOpenStatus()+" "+aBoolean);
                        controlView.setUpdateResult(view,aBoolean);
                    }
                });
    }

    public void getLoopStateByBuild(Context context){
        controlView.showWaiting();
        loopBiz.getLoopStateByBuilding(context,controlView.getBuildingID())
                .map(new Func1<HttpListData<HashMap<Long,Object>>, HashMap<Long,Object>>() {
                    @Override
                    public HashMap<Long,Object> call(HttpListData<HashMap<Long, Object>> hashMapHttpData) {
                        return hashMapHttpData.getData();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HashMap<Long, Object>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                       // controlView.getStateError();
                        controlView.hideWaiting();
                    }

                    @Override
                    public void onNext(HashMap<Long, Object> map) {
                       // controlView.setLoopState(map);
                        controlView.hideWaiting();
                    }
                });
    }

    public void getAirState(Context context,Long loopID){
        controlView.showWaiting();
        loopBiz.getAirLoopStateByID(context,loopID)
                .map(new Func1<HttpListData<ACCollect>, ACCollect>() {
                    @Override
                    public ACCollect call(HttpListData<ACCollect> acCollectHttpData) {
                        return acCollectHttpData.getData();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ACCollect>() {
                               @Override
                               public void onCompleted() {

                               }

                               @Override
                               public void onError(Throwable e) {
                                    Log.e("what",e.getMessage());
                                   controlView.getACError();
                                   controlView.hideWaiting();
                               }

                               @Override
                               public void onNext(ACCollect acCollect) {
                                    controlView.setACCollect(acCollect);
                                    controlView.hideWaiting();
                               }
                           }
                );
    }

    public void getScenes(Context context){
        //sceneBiz
    }

    public void getGroupControls(Context context){
        //sceneBiz
    }
}

package com.whucs.energyriver.Presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.whucs.energyriver.Bean.Building;
import com.whucs.energyriver.Bean.HttpData;
import com.whucs.energyriver.Bean.HttpResult;
import com.whucs.energyriver.Bean.Loop;
import com.whucs.energyriver.Bean.Tree;
import com.whucs.energyriver.Biz.BuildingBiz;
import com.whucs.energyriver.Biz.LoopBiz;
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
        controlView.showLoading();
        loopBiz.getLoopInfoByBuildID(context,controlView.getBuildingID())
                .map(new Func1<HttpData<List<Loop>>, List<Loop>>() {
                    @Override
                    public List<Loop> call(HttpData<List<Loop>> listHttpData) {
                        if(listHttpData.getData().size()!=0)
                            return listHttpData.getData();
                        else
                            return null;
                    }
                })
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

    public void getFirstBuildUnit(final Context context){
        controlView.showLoading();
        buildingBiz.getBuildingInfo(context)
                .map(new Func1<HttpData<List<Building>>, List<Building>>() {
                    @Override
                    public List<Building> call(HttpData<List<Building>> listHttpData) {
                        return listHttpData.getData();
                    }
                }).subscribeOn(Schedulers.io())
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
                    Building building = TreeUtil.getFirstChildID(buildings);
                    Tree tree = new Tree(buildings);
                    building.setBuildingName(tree.getBuildingPath(building));
                    controlView.setBuildingUnit(building);
                }
            });
    }

    public void updateLoopState(final View view,Context context){
        controlView.showWaiting();
        Log.e("what",controlView.getLoopID()+" "+controlView.getLoopState());
        loopBiz.updateLoop(context,controlView.getLoopID(),controlView.getLoopState())
                .map(new Func1<HttpResult, Boolean>() {
                    @Override
                    public Boolean call(HttpResult booleanHttpData) {
                        return booleanHttpData.isResult();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        controlView.hideWaiting();
                        controlView.updateError(view);

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        controlView.hideWaiting();
                        controlView.setUpdateResult(view,aBoolean);
                    }
                });
    }


}

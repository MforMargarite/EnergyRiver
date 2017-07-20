package com.whucs.energyriver.Presenter;

import android.content.Context;
import com.whucs.energyriver.Bean.Building;
import com.whucs.energyriver.Bean.HttpResult;
import com.whucs.energyriver.Biz.BuildingBiz;
import com.whucs.energyriver.Public.TreeUtil;
import com.whucs.energyriver.View.ChooseRoomView;
import java.util.ArrayList;
import java.util.List;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class ChooseRoomPresenter {
    private ChooseRoomView chooseRoomView;
    private BuildingBiz buildingBiz;

    public ChooseRoomPresenter(ChooseRoomView chooseRoomView){
        this.chooseRoomView = chooseRoomView;
        this.buildingBiz = new BuildingBiz();
    }


    public void getBuildingInfo(Context context){
        chooseRoomView.showWaiting();
        buildingBiz.getBuildingInfo(context)
                .map(new Func1<HttpResult<List<Building>>, List<Building>>() {
                    @Override
                    public List<Building> call(HttpResult<List<Building>> listHttpResult){
                        List<Building>buildings = listHttpResult.getData();
                        return buildings;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Building>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        chooseRoomView.execError();
                    }

                    @Override
                    public void onNext(List<Building> buildings) {
                        chooseRoomView.setBuildingInfo(buildings);
                    }
            });
    }

}

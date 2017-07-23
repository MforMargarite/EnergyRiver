package com.whucs.energyriver.Presenter;

import android.content.Context;
import com.whucs.energyriver.Bean.Building;
import com.whucs.energyriver.Bean.HttpData;
import com.whucs.energyriver.Bean.Tree;
import com.whucs.energyriver.Biz.BuildingBiz;
import com.whucs.energyriver.View.ChooseRoomView;

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
                .map(new Func1<HttpData<List<Building>>, Tree>() {
                    @Override
                    public Tree call(HttpData<List<Building>> listHttpData){
                        List<Building>buildings = listHttpData.getData();
                        return new Tree(buildings);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Tree>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        chooseRoomView.execError();
                    }

                    @Override
                    public void onNext(Tree tree) {
                        chooseRoomView.setBuildingInfo(tree);
                    }
            });
    }

}

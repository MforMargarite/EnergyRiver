package com.whucs.energyriver.Presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ToggleButton;

import com.whucs.energyriver.Bean.ACCollect;
import com.whucs.energyriver.Bean.Building;
import com.whucs.energyriver.Bean.HttpListData;
import com.whucs.energyriver.Bean.HttpResult;
import com.whucs.energyriver.Bean.Loop;
import com.whucs.energyriver.Bean.LoopStatus;
import com.whucs.energyriver.Bean.Scene;
import com.whucs.energyriver.Bean.Tree;
import com.whucs.energyriver.Biz.BuildingBiz;
import com.whucs.energyriver.Biz.LoopBiz;
import com.whucs.energyriver.Biz.SceneBiz;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.Public.TreeUtil;
import com.whucs.energyriver.R;
import com.whucs.energyriver.View.ControlView;
import com.whucs.energyriver.View.SceneView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class ControlPresenter {
    private ControlView controlView;
    private LoopBiz loopBiz;
    private SceneBiz sceneBiz;

    public ControlPresenter(ControlView controlView){
        this.controlView = controlView;
        this.loopBiz = new LoopBiz();
        this.sceneBiz = new SceneBiz();
    }

    //根据用户获取场景
    public void getScenes(Context context){
        sceneBiz.getScenes(context,Common.getID(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Scene>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        controlView.execError("获取场景列表失败,请检查网络后重试");
                    }

                    @Override
                    public void onNext(List<Scene> scenes) {
                        controlView.setSceneList(scenes);
                    }
                });
    }

    //根据用户获取组控
    public void getGroupControls(Context context){
        sceneBiz.getGroupControls(context,Common.getID(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Scene>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        controlView.execError("获取组控列表失败,请检查网络后重试");
                    }

                    @Override
                    public void onNext(List<Scene> scenes) {
                        controlView.setGroupControlList(scenes);
                    }
                });
    }

    //根据sceneID更新场景状态
    public void updateScene(final View view,final String type,Context context){
        //controlView.showHint("场景状态切换中...");
        final Long sceneID = Long.parseLong(view.getTag(R.id.identity).toString());
        sceneBiz.updateScene(context,sceneID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Map<String,Boolean>>() {
                    @Override
                    public void onCompleted() {
                        controlView.hideWaiting();
                    }

                    @Override
                    public void onError(Throwable e) {
                        controlView.hideWaiting();
                        controlView.updateError(view);
                        Log.e("what",e.getMessage());
                    }

                    @Override
                    public void onNext(Map<String,Boolean> map) {
                        Log.e("what","scene update result: "+map.get("result"));
                        controlView.setUpdateResult(view,type,map.get("result"));
                    }
                });
    }

    public void updateGroupControl(final View view,final String type,Context context){
        //controlView.showHint("组控状态切换中...");
        final Integer status = Integer.parseInt(view.getTag(R.id.state).toString());
        final Long sceneID = Long.parseLong(view.getTag(R.id.identity).toString());
        sceneBiz.updateGroupControl(context,sceneID,1-status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Map<String,Boolean>>() {
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
                    public void onNext(Map<String,Boolean> map) {
                        controlView.setUpdateResult(view,type,map.get("result"));
                    }
                });
    }


    //根据用户获取对应回路
    public void getLoopByUser(Context context){
        loopBiz.getLoopByUser(context, Common.getID(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<LoopStatus>>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        controlView.execError("获取可控回路失败，请刷新重试");
                        Log.e("what",e.getMessage());
                    }

                    @Override
                    public void onNext(List<LoopStatus> loops) {
                        controlView.setAllLoopList(loops);
                    }
                });
    }

    //更新回路状态
    public void updateLoopState(final View view,Context context){
        controlView.showHint("回路状态切换中...");
        final Long loopID = Long.parseLong(view.getTag(R.id.identity).toString());
        final Integer status = Integer.parseInt(view.getTag(R.id.state).toString());
        loopBiz.updateLoop(context,loopID,status)
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
                       controlView.setUpdateResult(view,null,aBoolean);
                    }
                });
    }


}

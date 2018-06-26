package com.whucs.energyriver.Presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.whucs.energyriver.Bean.HttpURL;
import com.whucs.energyriver.Bean.LoopStatus;
import com.whucs.energyriver.Bean.Scene;
import com.whucs.energyriver.Biz.SceneBiz;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.R;
import com.whucs.energyriver.View.SceneEditView;
import com.whucs.energyriver.View.SceneView;
import java.util.List;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ScenePresenter {
    private SceneBiz sceneBiz;
    private SceneView sceneView;
    private SceneEditView sceneEditView;

    public ScenePresenter(SceneView sceneView){
        this.sceneView = sceneView;
        this.sceneBiz = new SceneBiz();
    }

    public ScenePresenter(SceneEditView sceneEditView){
        this.sceneEditView = sceneEditView;
        this.sceneBiz = new SceneBiz();
    }

    public void getSceneList(Context context){
        sceneBiz.getScenes(context, Common.getID(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Scene>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        sceneView.execError();
                        Log.e("what",e.getMessage());
                    }

                    @Override
                    public void onNext(List<Scene> scenes) {
                        sceneView.setSceneList(scenes);
                    }
                });
    }

    public void getGroupControlList(Context context){
        sceneBiz.getGroupControls(context, Common.getID(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Scene>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        sceneView.execError();
                        Log.e("what",e.getMessage());
                    }

                    @Override
                    public void onNext(List<Scene> scenes) {
                        sceneView.setSceneList(scenes);
                    }
                });
    }

    public void delSceneById(Context context,final View view,final int position){
        TextView textView = (TextView) view.findViewById(R.id.scene_name);
        sceneBiz.delSceneById(context,Long.parseLong(textView.getTag(R.id.identity).toString()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Map<String, Boolean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        sceneView.delError();
                        Log.e("what",e.getMessage());
                    }

                    @Override
                    public void onNext(Map<String, Boolean> stringBooleanMap) {
                        sceneView.delSuccess(position);
                    }
                });
    }

    public void getControlLoops(Context context){
        sceneBiz.getControlLoops(context,Common.getID(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<LoopStatus>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        sceneEditView.execError("加载可控回路失败，请稍后重试");
                        Log.e("what",e.getMessage());
                    }

                    @Override
                    public void onNext(List<LoopStatus> loopStatuses) {
                        sceneEditView.setControlLoops(loopStatuses);
                    }
                });
    }

    public void addScene(Context context,String postString){
        sceneBiz.addSceneMode(context,postString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpURL>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        sceneEditView.postError();
                        Log.e("what",e.getMessage());
                    }

                    @Override
                    public void onNext(HttpURL httpURL) {
                        Log.e("what",httpURL.toString());
                        if(httpURL.isResult())
                            sceneEditView.postSuccess();
                        else
                            sceneEditView.postError();
                    }
                });
    }
}

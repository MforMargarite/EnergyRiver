package com.whucs.energyriver.Presenter;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.whucs.energyriver.Bean.HttpData;
import com.whucs.energyriver.Bean.HttpResult;
import com.whucs.energyriver.Bean.SubUser;
import com.whucs.energyriver.Biz.SubUserBiz;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.View.EditUserView;
import com.whucs.energyriver.View.SubUserView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class EditUserManagePresenter {
    SubUserBiz subUserBiz;
    EditUserView editUserView;

    public EditUserManagePresenter(EditUserView editUserView){
        this.editUserView = editUserView;
        subUserBiz = new SubUserBiz();
    }

    public void addSubUser(Context context){
        editUserView.showWaiting();
        subUserBiz.addSubUser(context,editUserView.getSubUser())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult>() {
                    @Override
                    public void onCompleted() {
                        editUserView.hideWaiting();
                    }

                    @Override
                    public void onError(Throwable e) {
                        editUserView.execError("添加失败");
                        Log.e("what",e.getMessage());
                        editUserView.hideWaiting();
                    }

                    @Override
                    public void onNext(HttpResult result) {
                        if(result.isResult())
                            editUserView.execSuccess("添加VIP子用户成功!");
                        else
                            editUserView.execError("添加失败");
                    }
                });

    }

    public void updateSubUser(Context context){
        editUserView.showWaiting();
        subUserBiz.updateSubUser(context,editUserView.getSubUser())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult>() {
                    @Override
                    public void onCompleted() {
                        editUserView.hideWaiting();
                    }

                    @Override
                    public void onError(Throwable e) {
                        editUserView.execError("更新失败");
                        editUserView.hideWaiting();
                    }

                    @Override
                    public void onNext(HttpResult result) {
                        if(result.isResult())
                            editUserView.execSuccess("修改VIP子用户信息成功!");
                        else
                            editUserView.execError("更新失败");
                    }
                });

    }

    public void deleteSubUser(Context context){
        editUserView.showWaiting();
        subUserBiz.deleteSubUser(context,editUserView.getSubUser().getUserID())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult>() {
                    @Override
                    public void onCompleted() {
                        editUserView.hideWaiting();
                    }

                    @Override
                    public void onError(Throwable e) {
                        editUserView.execError("删除失败");
                        editUserView.hideWaiting();
                    }

                    @Override
                    public void onNext(HttpResult result) {
                        if(result.isResult())
                            editUserView.execSuccess("删除VIP子用户成功!");
                        else
                            editUserView.execError("删除失败");
                    }
                });

    }


}

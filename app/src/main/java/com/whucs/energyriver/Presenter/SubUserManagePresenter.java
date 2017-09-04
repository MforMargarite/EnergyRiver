package com.whucs.energyriver.Presenter;


import android.content.Context;

import com.whucs.energyriver.Bean.HttpData;
import com.whucs.energyriver.Bean.SubUser;
import com.whucs.energyriver.Biz.SubUserBiz;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.View.SubUserView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class SubUserManagePresenter {
    SubUserBiz subUserBiz;
    SubUserView subUserView;

    public SubUserManagePresenter(SubUserView subUserView){
        this.subUserView = subUserView;
        subUserBiz = new SubUserBiz();
    }

    public void getSubUser(Context context){
        subUserBiz.getSubUser(context, Common.getID(context))
                .subscribeOn(Schedulers.io())
                .map(new Func1<HttpData<List<SubUser>>, List<SubUser>>() {
                    @Override
                    public List<SubUser> call(HttpData<List<SubUser>> listHttpData) {
                        return listHttpData.getData();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<SubUser>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        subUserView.execError("获取子用户失败");
                    }

                    @Override
                    public void onNext(List<SubUser> users) {
                        subUserView.getSubSuccess(users);
                    }
                });
    }

}

package com.whucs.energyriver.Presenter;

import android.content.Context;
import android.util.Log;

import com.whucs.energyriver.Biz.MessageBiz;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.View.ChangeMobileView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MessagePresenter {
    private ChangeMobileView changeMobileView;
    private MessageBiz messageBiz;

    public MessagePresenter(ChangeMobileView mobileView){
        this.changeMobileView = mobileView;
        messageBiz = new MessageBiz();
    }


    public void getVerifyCode(Context context){
        messageBiz.getVerifyCode(context, Common.getMsgId(),Common.getMsgName(),Common.getMsgPwd(),Common.getMessage(),changeMobileView.getPhone(),Common.getTimestamp())
                .map(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        int result = Integer.parseInt(s.split("State:")[1].split(",")[0]);
                        if(result == 1)
                            return true;
                        else
                            return false;
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
                        changeMobileView.postFail();
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if(aBoolean)
                            changeMobileView.postSuccess();
                        else
                            changeMobileView.postFail();
                    }
                });
    }


}

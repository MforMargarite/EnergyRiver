package com.whucs.energyriver.Presenter;

import android.content.Context;
import android.util.Log;

import com.whucs.energyriver.Bean.PwdState;
import com.whucs.energyriver.Biz.UserBiz;
import com.whucs.energyriver.View.ChangePwdView;
import org.json.JSONObject;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class ChangePwdPresenter {
    private ChangePwdView changePwdView;
    private UserBiz userBiz;

    public ChangePwdPresenter(ChangePwdView changePwdView){
        this.changePwdView = changePwdView;
        this.userBiz = new UserBiz();
    }

    public void changePwd(Context context){
        changePwdView.showWaiting();
        userBiz.changePwd(context,changePwdView.getUsername(),changePwdView.getPwd(),changePwdView.getNewPwd())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PwdState>() {
                    @Override
                    public void onCompleted() {
                        changePwdView.hideWaiting();
                    }

                    @Override
                    public void onError(Throwable e) {
                        changePwdView.execError(e.getMessage());
                        changePwdView.hideWaiting();
                    }

                    @Override
                    public void onNext(PwdState result) {
                        Log.e("what",result.toString());
                        try {
                            if (result.isSuccess()) {
                                changePwdView.changePwdSuccess();
                            } else
                                changePwdView.execError(result.getMessage());
                        }catch (Exception e){
                            e.printStackTrace();
                            changePwdView.execError("密码修改失败,请检查网络后重试");
                        }
                    }
                });
    }
}

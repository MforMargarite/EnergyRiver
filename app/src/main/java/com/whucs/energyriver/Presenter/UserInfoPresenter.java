package com.whucs.energyriver.Presenter;

import android.content.Context;
import android.util.Log;

import com.whucs.energyriver.Bean.HttpCode;
import com.whucs.energyriver.Bean.HttpResult;
import com.whucs.energyriver.Biz.UserBiz;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.View.UserInfoView;
import org.json.JSONObject;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;



public class UserInfoPresenter {
    private UserInfoView userInfoView;
    private UserBiz userBiz;

    public UserInfoPresenter(UserInfoView userInfoView){
        this.userInfoView = userInfoView;
        this.userBiz = new UserBiz();
    }

    public void updateUsername(Context context)throws Exception{
        userInfoView.showWaiting();
        userBiz.updateUserName(context,userInfoView.getUsername(), Common.getID(context))
                .subscribeOn(Schedulers.io())
                .map(new Func1<HttpCode, Boolean>() {
                    @Override
                    public Boolean call(HttpCode code) {
                        return (code.getCode() == 200);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {
                        userInfoView.hideWaiting();
                    }

                    @Override
                    public void onError(Throwable e) {
                        userInfoView.execError(e.getMessage());
                        userInfoView.hideWaiting();
                    }

                    @Override
                    public void onNext(Boolean result) {
                        if(result){
                            userInfoView.uploadUsernameSuccess(userInfoView.getUsername());
                        }else
                            userInfoView.execError("用户名更新失败,请检查网络后重试");
                    }
                });
    }

    private String byteArray2PostAvatar(byte[] avatar_byte){
        //将byte[]包装成后台可识别的avatar data
        String postAvatar = new String(avatar_byte);
        return postAvatar;
    }

    public void uploadAvatar(Context context){
        userInfoView.showWaiting();
        userBiz.uploadAvatar(context,byteArray2PostAvatar(userInfoView.getAvatarData()))
                .subscribeOn(Schedulers.io())
                .map(new Func1<HttpResult, Boolean>() {
                    @Override
                    public Boolean call(HttpResult result) {
                        return result.isResult();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {
                        userInfoView.hideWaiting();
                    }

                    @Override
                    public void onError(Throwable e) {
                        userInfoView.execError(e.getMessage());
                        userInfoView.hideWaiting();
                    }

                    @Override
                    public void onNext(Boolean result) {
                        if(result){
                            userInfoView.uploadAvatarSuccess(userInfoView.getAvatarData());
                        }else
                            userInfoView.execError("头像更新失败,请检查网络后重试");
                    }
                });
    }


}

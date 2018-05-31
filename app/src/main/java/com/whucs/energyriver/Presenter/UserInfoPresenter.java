package com.whucs.energyriver.Presenter;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.whucs.energyriver.Bean.HttpCode;
import com.whucs.energyriver.Bean.HttpResult;
import com.whucs.energyriver.Bean.HttpURL;
import com.whucs.energyriver.Biz.UserBiz;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.View.UserInfoView;
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

    public void updateAvatarURL(Context context){
        userInfoView.showWaiting();
        userBiz.updateAvatarURL(context,userInfoView.getAvatarURL(), Common.getID(context),Common.getBranchID(context))
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
                        userInfoView.execError("头像更新失败，请稍后重试");
                        userInfoView.hideWaiting();
                        Log.e("what",e.getMessage());
                    }

                    @Override
                    public void onNext(Boolean result) {
                        if(result){
                            userInfoView.updateURLSuccess(userInfoView.getAvatarData());
                        }else
                            userInfoView.execError("头像更新失败,请稍后重试");
                    }
                });
    }

    public void updateUsername(Context context)throws Exception{
        userInfoView.showWaiting();
        userBiz.updateUserName(context,userInfoView.getUsername(), Common.getID(context),Common.getBranchID(context))
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
                        userInfoView.execError("用户名更新失败,请稍后重试");
                        userInfoView.hideWaiting();
                        Log.e("what",e.getMessage());
                    }

                    @Override
                    public void onNext(Boolean result) {
                        if(result){
                            userInfoView.uploadUsernameSuccess();
                        }else
                            userInfoView.execError("用户名更新失败,请稍后重试");
                    }
                });
    }

    private String byteArray2PostAvatar(byte[] avatar_byte){
        //将byte[]包装成后台可识别的avatar data
        byte[] postAvatar = Base64.encode(avatar_byte, Base64.DEFAULT);
        StringBuilder builder = new StringBuilder();
        builder.append("***********************").append(new String(postAvatar));//23位前缀
        return builder.toString();
    }

    public void uploadAvatar(Context context){
        userInfoView.showWaiting();
        userBiz.uploadAvatar(context,byteArray2PostAvatar(userInfoView.getAvatarData()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpURL>() {
                    @Override
                    public void onCompleted() {
                        userInfoView.hideWaiting();
                    }

                    @Override
                    public void onError(Throwable e) {
                        userInfoView.execError("上传头像至服务器失败,请稍后重试");
                        userInfoView.hideWaiting();
                        Log.e("what",e.getMessage());
                    }

                    @Override
                    public void onNext(HttpURL result) {
                      //  Log.e("what",result.toString());
                        if(result.isResult()){
                            userInfoView.uploadAvatarSuccess(result.getData().toString());
                        }else
                            userInfoView.execError("上传头像至服务器失败,请稍后重试");
                    }
                });
    }

    public void updateMobile(Context context){
        userInfoView.showWaiting();
        userBiz.updateMobile(context,userInfoView.getMobile(), Common.getID(context),Common.getBranchID(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpCode>() {
                    @Override
                    public void onCompleted() {
                        userInfoView.hideWaiting();
                    }

                    @Override
                    public void onError(Throwable e) {
                        userInfoView.execError("手机号修改失败,请稍后重试");
                        userInfoView.hideWaiting();
                        Log.e("what",e.getMessage());
                    }

                    @Override
                    public void onNext(HttpCode result) {
                        try {
                            if (result.getCode() == 200) {
                                userInfoView.changeMobileSuccess();
                            } else
                                userInfoView.execError("手机号修改失败,请稍后重试");
                        }catch (Exception e){
                            e.printStackTrace();
                            userInfoView.execError("手机号修改失败,请稍后重试");
                        }
                    }
                });
    }

}

package com.whucs.energyriver.Presenter;

import android.content.Context;
import android.util.Log;

import com.whucs.energyriver.Bean.HttpResult;
import com.whucs.energyriver.Biz.NoticeBiz;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.View.UpdateNoticeView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UpdateNoticePresenter {
    private NoticeBiz noticeBiz;
    private UpdateNoticeView noticeView;

    public UpdateNoticePresenter(UpdateNoticeView view){
        this.noticeView = view;
        this.noticeBiz = new NoticeBiz();
    }

    public void updateNoticeState(Context context){
        noticeBiz.updateNotice(context, noticeView.getNoticeID(),1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("what",e.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult result) {
                        noticeView.updateSuccess();
                    }
                });
    }
}

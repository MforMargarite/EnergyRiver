package com.whucs.energyriver.Presenter;

import android.content.Context;
import android.util.Log;

import com.whucs.energyriver.Bean.HttpListData;
import com.whucs.energyriver.Bean.Notice;
import com.whucs.energyriver.Biz.NoticeBiz;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.View.NoticeView;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class NoticePresenter {
    private NoticeBiz noticeBiz;
    private NoticeView noticeView;

    public NoticePresenter(NoticeView noticeView){
        this.noticeView = noticeView;
        this.noticeBiz = new NoticeBiz();
    }

    public void getNoticeByType(Context context, final int type,int pageIndex,int pageSize){
        noticeBiz.getNoticeByTypeAndPage(context, Common.getID(context),type,pageIndex,pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpListData<List<Notice>>>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        noticeView.execError("获取事件通知失败,请检查网络后重试");
                    }

                    @Override
                    public void onNext(HttpListData<List<Notice>> notices) {
                        noticeView.setNoticeList(notices.getData(),type,notices.getTotal());
                    }
                });
    }

    public void getNoticeByTypeAppend(Context context, int type, int pageIndex, int pageSize){
        noticeBiz.getNoticeByTypeAndPage(context, Common.getID(context),type,pageIndex,pageSize)
                .subscribeOn(Schedulers.io())
                .map(new Func1<HttpListData<List<Notice>>, List<Notice>>() {
                    @Override
                    public List<Notice> call(HttpListData<List<Notice>> noticeHttpListData) {
                        return noticeHttpListData.getData();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Notice>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        noticeView.execError("获取事件通知失败,请检查网络后重试");
                    }

                    @Override
                    public void onNext(List<Notice> notices) {
                        noticeView.setNoticeListAppend(notices);
                    }
                });
    }
}

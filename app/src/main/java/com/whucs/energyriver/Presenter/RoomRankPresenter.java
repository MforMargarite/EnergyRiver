package com.whucs.energyriver.Presenter;


import android.content.Context;

import com.whucs.energyriver.Bean.HttpData;
import com.whucs.energyriver.Bean.RoomRank;
import com.whucs.energyriver.Biz.RankBiz;
import com.whucs.energyriver.View.RoomRankView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class RoomRankPresenter {
    private RoomRankView roomRankView;
    private RankBiz rankBiz;

    public RoomRankPresenter(RoomRankView roomRankView){
        this.roomRankView = roomRankView;
        this.rankBiz = new RankBiz();
    }

    public void getRoomRank(Context context){
        rankBiz.getRoomRank(context)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Func1<HttpData<List<RoomRank>>, List<RoomRank>>() {
                    @Override
                    public List<RoomRank> call(HttpData<List<RoomRank>> listHttpData) {
                        return listHttpData.getData();
                    }
                }).subscribe(new Observer<List<RoomRank>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        roomRankView.getRankError();
                    }

                    @Override
                    public void onNext(List<RoomRank> roomRanks) {
                        roomRankView.setRoomRank(roomRanks);
                    }
                });
    }
}

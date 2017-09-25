package com.whucs.energyriver.Presenter;


import android.content.Context;
import android.util.Log;

import com.whucs.energyriver.Bean.HttpData;
import com.whucs.energyriver.Bean.Percent;
import com.whucs.energyriver.Bean.Rank;
import com.whucs.energyriver.Biz.RankBiz;
import com.whucs.energyriver.View.PercentView;
import com.whucs.energyriver.View.RankView;
import java.util.List;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;



public class RankPresenter {
    private RankView rankView;
    private PercentView percentView;
    private RankBiz rankBiz;

    public RankPresenter(RankView rankView){
        this.rankView = rankView;
        this.rankBiz = new RankBiz();
    }

    public RankPresenter(PercentView percentView){
        this.percentView = percentView;
        this.rankBiz = new RankBiz();
    }

    public void getRoomRank(Context context){
        rankBiz.getRoomRank(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<HttpData<List<Rank>>, List<Rank>>() {
                    @Override
                    public List<Rank> call(HttpData<List<Rank>> listHttpData) {
                        return listHttpData.getData();
                    }
                }).subscribe(new Observer<List<Rank>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("- -",e.getMessage());
                        rankView.getRankError();
                    }

                    @Override
                    public void onNext(List<Rank> ranks) {
                        rankView.setRank(ranks);
                    }
                });
    }

    public void getBranchRank(Context context){
        rankBiz.getBranchRank(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<HttpData<List<Rank>>, List<Rank>>() {
                    @Override
                    public List<Rank> call(HttpData<List<Rank>> listHttpData) {
                        return listHttpData.getData();
                    }
                }).subscribe(new Observer<List<Rank>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("- -",e.getMessage());
                rankView.getRankError();
            }

            @Override
            public void onNext(List<Rank> ranks) {
                rankView.setRank(ranks);
            }
        });
    }


    public void getDeviceRank(Context context){
        rankBiz.getDeviceRank(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<HttpData<List<Rank>>, List<Rank>>() {
                    @Override
                    public List<Rank> call(HttpData<List<Rank>> listHttpData) {
                        return listHttpData.getData();
                    }
                }).subscribe(new Observer<List<Rank>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("- -",e.getMessage());
                rankView.getRankError();
            }

            @Override
            public void onNext(List<Rank> ranks) {
                rankView.setRank(ranks);
            }
        });
    }

    public void  getMobileEnergyByType(Context context){
        rankBiz. getMobileEnergyByType(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<HttpData<List<Percent>>, List<Percent>>() {
                    @Override
                    public List<Percent> call(HttpData<List<Percent>> listHttpData) {
                        return listHttpData.getData();
                    }
                }).subscribe(new Observer<List<Percent>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("- -",e.getMessage());
                percentView.getPercentError();
            }

            @Override
            public void onNext(List<Percent> data) {
                percentView.setPercent(data);
            }
        });
    }
}

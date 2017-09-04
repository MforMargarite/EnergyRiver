package com.whucs.energyriver.Presenter;


import android.content.Context;

import com.whucs.energyriver.Bean.Bill;
import com.whucs.energyriver.Biz.BillBiz;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.View.BillView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BillPresenter {
    private BillView billView;
    private BillBiz billBiz;

    public BillPresenter(BillView billView){
        this.billView = billView;
        this.billBiz = new BillBiz();
    }

    public void getBillByUser(Context context){
        billBiz.getBillByUser(context, Common.getID(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Bill>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        billView.execError("获取账单列表失败,请检查网络后重试");
                    }

                    @Override
                    public void onNext(List<Bill> bills) {
                        billView.getBillSuccess(bills);
                    }
                });
    }
}

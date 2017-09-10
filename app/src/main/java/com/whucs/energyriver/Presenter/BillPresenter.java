package com.whucs.energyriver.Presenter;


import android.content.Context;
import android.util.Log;

import com.whucs.energyriver.Bean.Bill;
import com.whucs.energyriver.Bean.HttpData;
import com.whucs.energyriver.Biz.BillBiz;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.View.BillView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class BillPresenter {
    private BillView billView;
    private BillBiz billBiz;

    public BillPresenter(BillView billView){
        this.billView = billView;
        this.billBiz = new BillBiz();
    }

    public void getBillByUser(Context context){
        billBiz.getBillByUser(context, Common.getID(context),Common.getPwd(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<HttpData<List<Bill>>,List<Bill>>() {
                    @Override
                    public List<Bill> call(HttpData<List<Bill>> listHttpData) {
                        return listHttpData.getData();
                    }
                })
                .subscribe(new Observer<List<Bill>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("what",e.getMessage());
                        billView.execError("获取账单列表失败,请检查网络后重试");
                    }

                    @Override
                    public void onNext(List<Bill> bills) {
                        billView.getBillSuccess(bills);
                    }
                });
    }
}

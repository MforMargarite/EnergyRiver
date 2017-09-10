package com.whucs.energyriver.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.whucs.energyriver.Bean.Bill;
import com.whucs.energyriver.Bean.SubUser;
import com.whucs.energyriver.R;

import java.util.ArrayList;
import java.util.List;


public class BillAdapter extends BaseAdapter{
    private Context context;
    private List<Bill>billList;
    private Resources res;

    public BillAdapter(Context context, List<Bill> billList) {
        this.context = context;
        this.res = context.getResources();
        this.billList = billList;
    }

    @Override
    public int getCount() {
        return billList.size();
    }

    @Override
    public Object getItem(int i) {
        return billList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return billList.get(i).getBillID();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        Bill bill = billList.get(i);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.bill_item, null);
            holder = new ViewHolder();
            holder.bill_time = (TextView) view.findViewById(R.id.bill_time);
            holder.bill_num = (TextView) view.findViewById(R.id.bill_num);
            holder.bill_status = (TextView) view.findViewById(R.id.bill_status);
            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();
        holder.bill_time.setText(bill.getYearMonth());
        holder.bill_num.setText(bill.getBasicCost()+"元");
        holder.bill_status.setText(bill.getState()>1?"已缴费":"未缴费");

        return view;
    }



    class ViewHolder{
        TextView bill_time;
        TextView bill_num;
        TextView bill_status;
    }

}

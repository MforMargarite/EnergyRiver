package com.whucs.energyriver.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.whucs.energyriver.Bean.Rank;
import com.whucs.energyriver.R;

import java.util.List;


public class RankAdapter extends BaseAdapter {
    private Context context;
    private List<Rank>list;
    private Drawable[] tendency;
    private int[] tendencyID = {R.mipmap.up,R.mipmap.down,R.mipmap.remain};

    public RankAdapter(Context context, List<Rank>list){
        this.context = context;
        this.list = list;
        Resources res = context.getResources();
        tendency = new Drawable[3];
        for(int i=0;i<3;i++){
            tendency[i] = res.getDrawable(tendencyID[i]);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.rank_item,null);
            viewHolder = new ViewHolder();
            viewHolder.user = (TextView) view.findViewById(R.id.name);
            viewHolder.percent = (TextView) view.findViewById(R.id.percent);
            viewHolder.money = (TextView) view.findViewById(R.id.money);
            viewHolder.upOrDown = (ImageView) view.findViewById(R.id.upOrDown);
            view.setTag(viewHolder);
        }else
            viewHolder = (ViewHolder) view.getTag();
        Rank rank = list.get(i);
        viewHolder.user.setText(rank.getName());
        viewHolder.money.setText(rank.getMoney());
        viewHolder.percent.setText(rank.getPercent());
        viewHolder.upOrDown.setImageDrawable(tendency[rank.getUpOrDown()]);
        return view;
    }

    class ViewHolder{
        ImageView upOrDown;
        TextView user;
        TextView money;
        TextView percent;
    }
}

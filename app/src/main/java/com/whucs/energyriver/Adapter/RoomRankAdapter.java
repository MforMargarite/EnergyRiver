package com.whucs.energyriver.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.whucs.energyriver.Bean.RoomRank;
import com.whucs.energyriver.R;

import java.util.List;


public class RoomRankAdapter extends BaseAdapter {
    private Context context;
    private List<RoomRank>list;

    public RoomRankAdapter(Context context,List<RoomRank>list){
        this.context = context;
        this.list = list;
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
            view = LayoutInflater.from(context).inflate(R.layout.room_rank_item,null);
            viewHolder = new ViewHolder();
            viewHolder.user = (TextView) view.findViewById(R.id.user);
            viewHolder.location = (TextView) view.findViewById(R.id.location);
            viewHolder.dept = (TextView) view.findViewById(R.id.dept);
            viewHolder.money = (TextView) view.findViewById(R.id.money);
            view.setTag(viewHolder);
        }else
            viewHolder = (ViewHolder) view.getTag();
        RoomRank rank = list.get(i);
        viewHolder.user.setText(rank.getUser());
        viewHolder.location.setText(rank.getLocation());
        viewHolder.dept.setText(rank.getDept());
        viewHolder.money.setText(rank.getMoney());
        return view;
    }

    class ViewHolder{
        TextView user;
        TextView location;
        TextView dept;
        TextView money;
    }
}

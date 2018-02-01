package com.whucs.energyriver.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.whucs.energyriver.Bean.Building;
import com.whucs.energyriver.R;
import java.util.List;


public class RoomAdapter extends BaseAdapter{
    private Context context;
    private List<Building>buildList;

    public RoomAdapter(Context context, List<Building> buildList) {
        this.context = context;
        this.buildList = buildList;
    }

    @Override
    public int getCount() {
        return buildList.size();
    }

    @Override
    public Object getItem(int i) {
        return buildList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return buildList.get(i).getBuildingID();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        Building build = buildList.get(i);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.textview_item, null);
            holder = new ViewHolder();
            holder.content = (TextView) view.findViewById(R.id.content);
            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();
        holder.content.setText(build.getBuildingName());
        return view;
    }


    class ViewHolder{
        TextView content;
    }

}

package com.whucs.energyriver.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.whucs.energyriver.Bean.LoopStatus;
import com.whucs.energyriver.R;
import java.util.ArrayList;
import java.util.List;


public class LoopAdapter extends BaseAdapter {
    private Context context;
    private List<LoopStatus>list;
    private View.OnClickListener clickListener;
    private Resources res;

    public LoopAdapter(Context context, List<LoopStatus>list, View.OnClickListener clickListener){
        this.context = context;
        this.res = context.getResources();
        this.clickListener = clickListener;
        if (list == null || list.size() == 0) {
            list = new ArrayList<>();
            LoopStatus loop = new LoopStatus();
            loop.setLoopID(-1L);
            list.add(loop);
        }
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
        //Loop loop = list.get(i);
        LoopStatus loop = list.get(i);
        if(loop.getLoopID() == -1L){
            view = LayoutInflater.from(context).inflate(R.layout.info, null);
            TextView content = (TextView) view.findViewById(R.id.content);
            content.setText(res.getText(R.string.no_loop_item));
            content.setPadding(0,(int)res.getDimension(R.dimen.empty_loop_padding),0,(int)res.getDimension(R.dimen.building_list_padding));
        }else {
            ViewHolder holder;
            if(view == null){
                view = LayoutInflater.from(context).inflate(R.layout.detail_control_item, null);
                holder = new ViewHolder();
                holder.cate_name = (TextView) view.findViewById(R.id.cate_name);
                holder.toggle = (ToggleButton) view.findViewById(R.id.switcher);
                holder.toggle.setOnClickListener(clickListener);
                view.setTag(holder);
            }else{
                holder = (ViewHolder) view.getTag();
            }
            holder.cate_name.setText(loop.getLoopName());
            if (loop.getOpenStatus() != null)
                holder.toggle.setChecked(loop.getOpenStatus());
            holder.toggle.setTag(R.id.state,loop.getOpenStatus());
            holder.toggle.setTag(R.id.identity,loop.getLoopID());
        }
        return view;
    }

    class ViewHolder{
        TextView cate_name;
        ToggleButton toggle;
    }



}

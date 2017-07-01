package com.whucs.energyriver.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.whucs.energyriver.Bean.Loop;
import com.whucs.energyriver.Bean.RoomRank;
import com.whucs.energyriver.R;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class LoopAdapter extends BaseAdapter {
    private Context context;
    private List<Loop>list;
    private final String[] types = {"照明","空调","插座"};
    private final int[] cate_icon = {R.mipmap.light,R.mipmap.air_condition,R.mipmap.socket};
    private Resources res;
    public LoopAdapter(Context context, List<Loop>list){
        this.context = context;
        this.list = convertList(list);
        this.res = context.getResources();
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
        Loop loop = list.get(i);
        if(loop.getLoopID() == 0L) {
            view = LayoutInflater.from(context).inflate(R.layout.cate_control_item, null);
            TextView cate_name = (TextView) view.findViewById(R.id.cate_name);
            ImageView cate_img = (ImageView)view.findViewById(R.id.cate_img);
            cate_name.setText(loop.getLoopName());
            cate_img.setImageDrawable(res.getDrawable(cate_icon[Integer.parseInt(loop.getLoopTypeID().toString()) - 1]));
        }else if(loop.getLoopTypeID() == 2L){
            view = LayoutInflater.from(context).inflate(R.layout.air_detail_control_item, null);
            TextView cate_name = (TextView) view.findViewById(R.id.cate_name);
            ImageView toggle = (ImageView) view.findViewById(R.id.toggle);
            cate_name.setText(loop.getLoopName());
            toggle.setTag(loop.getLoopID());
        }else{
            view = LayoutInflater.from(context).inflate(R.layout.detail_control_item, null);
            TextView cate_name = (TextView) view.findViewById(R.id.cate_name);
            ToggleButton toggle = (ToggleButton) view.findViewById(R.id.toggle);
            cate_name.setText(loop.getLoopName());
            toggle.setTag(loop.getLoopID());
        }
        return view;
    }

    private List<Loop> convertList(List<Loop> list){
        Long loopType = 0L;
        int listSize = list.size();
        for (int i=0;i<listSize;i++) {
            Loop item = list.get(i);
            if (!item.getLoopTypeID().equals(loopType)) {
                loopType = item.getLoopTypeID();
                Loop type = new Loop();
                type.setLoopID(0L);
                type.setLoopName(types[Integer.parseInt(loopType.toString()) - 1]);
                type.setLoopTypeID(loopType);
                list.add(i, type);
                listSize = list.size();
            }
        }
        Log.e("what",list.toString());
        return list;
    }
}

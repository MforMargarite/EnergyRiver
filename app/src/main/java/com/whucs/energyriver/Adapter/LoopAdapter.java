package com.whucs.energyriver.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.whucs.energyriver.Bean.Loop;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.R;
import java.util.ArrayList;
import java.util.List;


public class LoopAdapter extends BaseAdapter {
    private Context context;
    private List<Loop>list;
    private View.OnClickListener clickListener;
    private Resources res;

    public LoopAdapter(Context context, List<Loop>list, View.OnClickListener clickListener){
        this.context = context;
        this.res = context.getResources();
        this.clickListener = clickListener;
        if (list == null) {
            list = new ArrayList<>();
            Loop loop = new Loop();
            loop.setLoopID(-1L);
            list.add(loop);
            this.list = list;
        }else
            this.list = convertList(list);
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
        if(loop.getLoopID() == -1L){
            view = LayoutInflater.from(context).inflate(R.layout.textview_item, null);
            TextView content = (TextView) view.findViewById(R.id.content);
            content.setText(res.getText(R.string.no_loop_item));
            content.setPadding(0,(int)res.getDimension(R.dimen.building_list_padding),0,(int)res.getDimension(R.dimen.building_list_padding));
        }else
            if (loop.getLoopID() == 0L) {
                view = LayoutInflater.from(context).inflate(R.layout.cate_control_item, null);
                TextView cate_name = (TextView) view.findViewById(R.id.cate_name);
                ImageView cate_img = (ImageView) view.findViewById(R.id.cate_img);
                cate_name.setText(loop.getLoopName());
                cate_img.setImageDrawable(res.getDrawable(Common.cate_icon[Integer.parseInt(loop.getLoopTypeID().toString()) - 1]));
            } else if (loop.getLoopTypeID() == 2L) {
                view = LayoutInflater.from(context).inflate(R.layout.air_detail_control_item, null);
                TextView cate_name = (TextView) view.findViewById(R.id.cate_name);
                ImageView toggle = (ImageView) view.findViewById(R.id.toggle);
                toggle.setOnClickListener(clickListener);
                cate_name.setText(loop.getLoopName());
                toggle.setTag(loop.getLoopID());
            } else {
                view = LayoutInflater.from(context).inflate(R.layout.detail_control_item, null);
                TextView cate_name = (TextView) view.findViewById(R.id.cate_name);
                ToggleButton toggle = (ToggleButton) view.findViewById(R.id.switcher);
                if(loop.getOpenStatus()!=null)
                    toggle.setChecked(loop.getOpenStatus());
                toggle.setOnClickListener(clickListener);
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
            if (item.getLoopID()!=0L && !item.getLoopTypeID().equals(loopType)) {
                //如果item不是回路类型且其回路类型是新的
                loopType = item.getLoopTypeID();
                Loop type = new Loop();
                type.setLoopID(0L);
                type.setLoopName(Common.types[Integer.parseInt(loopType.toString()) - 1]);
                type.setLoopTypeID(loopType);
                list.add(i, type);
                listSize = list.size();
            }
        }
        return list;
    }


}

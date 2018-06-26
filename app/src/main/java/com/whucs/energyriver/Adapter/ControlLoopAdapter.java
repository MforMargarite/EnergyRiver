package com.whucs.energyriver.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.whucs.energyriver.Bean.LoopStatus;
import com.whucs.energyriver.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ControlLoopAdapter extends BaseAdapter implements View.OnClickListener,CompoundButton.OnCheckedChangeListener{
    private Context context;
    private List<LoopStatus>list;
    private Resources res;
    private int type;//1-场景 2-组控
    private boolean[] chosen;
    private boolean[] states;

    public ControlLoopAdapter(Context context, int type,List<LoopStatus>list){
        this.context = context;
        this.res = context.getResources();
        this.type = type;

        if (list == null || list.size() == 0) {
            list = new ArrayList<>();
            LoopStatus loop = new LoopStatus();
            loop.setLoopID(-1L);
            list.add(loop);
        }
        this.list = list;
        this.chosen = new boolean[list.size()];
        this.states = new boolean[list.size()];
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
        LoopStatus loop = list.get(i);
        if(loop.getLoopID() == -1L){
            view = LayoutInflater.from(context).inflate(R.layout.info, null);
            TextView content = (TextView) view.findViewById(R.id.content);
            content.setText(res.getText(R.string.no_loop_item));
            content.setPadding(0,(int)res.getDimension(R.dimen.empty_loop_padding),0,(int)res.getDimension(R.dimen.building_list_padding));
        }else {
            ViewHolder holder;
            if(view == null){
                view = LayoutInflater.from(context).inflate(R.layout.control_loop_item, null);
                holder = new ViewHolder();
                holder.loop_name = (TextView) view.findViewById(R.id.loop_name);
                holder.checker = (AppCompatCheckBox) view.findViewById(R.id.checker);
                holder.checker.setOnCheckedChangeListener(this);
                holder.toggle = (ToggleButton) view.findViewById(R.id.switcher);
                holder.toggle.setOnClickListener(this);
                view.setTag(holder);
            }else{
                holder = (ViewHolder) view.getTag();
            }
            holder.loop_name.setText(loop.getLoopName());
            if(type != 1)
                holder.toggle.setVisibility(View.INVISIBLE);
            holder.checker.setTag(R.id.identity,loop.getLoopID());
            holder.checker.setTag(R.id.position,i);
            holder.toggle.setTag(R.id.position,i);

            holder.checker.setChecked(chosen[i]);
            holder.toggle.setChecked(states[i]);
        }
        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        int pos = Integer.parseInt(compoundButton.getTag(R.id.position).toString());
        chosen[pos] = b;
    }

    @Override
    public void onClick(View view) {
        ToggleButton switcher = (ToggleButton) view;
        int pos = Integer.parseInt(view.getTag(R.id.position).toString());
        states[pos] = switcher.isChecked();
    }

    class ViewHolder{
        AppCompatCheckBox checker;
        TextView loop_name;
        ToggleButton toggle;
    }

    public List<Map<String,Object>> getControlLoops(){
        List<Map<String,Object>>result = new ArrayList<>();
        int count = getCount();
        for(int i = 0;i<count;i++){
            if(chosen[i]){
                Map<String,Object>map = new HashMap<>();
                map.put("loopID",list.get(i).getLoopID());
                map.put("equipmentStatus",states[i]?1:0);
                result.add(map);
            }
        }
        return result;
    }


}

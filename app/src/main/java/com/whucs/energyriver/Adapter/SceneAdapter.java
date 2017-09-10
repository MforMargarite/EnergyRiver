package com.whucs.energyriver.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.whucs.energyriver.Bean.Bill;
import com.whucs.energyriver.Bean.Scene;
import com.whucs.energyriver.R;
import com.whucs.energyriver.Widget.CircleView;

import java.util.ArrayList;
import java.util.List;


public class SceneAdapter extends BaseAdapter{
    private Context context;
    private List<Scene>sceneList;
    private Resources res;

    public SceneAdapter(Context context, List<Scene> sceneList) {
        this.context = context;
        this.res = context.getResources();
        if(sceneList == null)
            sceneList = new ArrayList<>();
        //添加 + 作为场景添加按钮 判断条件：sceneID == -1
        Scene scene = new Scene();
        scene.setID(-1L);
        sceneList.add(scene);
        //初始化
        this.sceneList = sceneList;
    }

    @Override
    public int getCount() {
        return sceneList.size();
    }

    @Override
    public Object getItem(int i) {
        return sceneList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return sceneList.get(i).getID();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        Scene scene = sceneList.get(i);
        if(view == null){
            view = new CircleView(context);
            holder = new ViewHolder();
            holder.scene = (CircleView) view;
            view.setTag(holder);
        }else
            holder = (ViewHolder) view.getTag();
        if(scene.getID() == -1L) {
            holder.scene.setText("+");
        }else{
            holder.scene.setText(scene.getName());
        }
        return view;
    }



    class ViewHolder{
        CircleView scene;
    }

}

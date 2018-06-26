package com.whucs.energyriver.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.whucs.energyriver.Bean.Scene;
import com.whucs.energyriver.R;
import com.whucs.energyriver.Widget.SceneView;

import java.util.ArrayList;
import java.util.List;


public class SceneManageAdapter extends BaseAdapter{
    private Context context;
    private List<Scene>sceneList;

    public SceneManageAdapter(Context context, List<Scene> sceneList) {
        this.context = context;
        if(sceneList == null)
            sceneList = new ArrayList<>();
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
        return sceneList.get(i).getSceneID();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        Scene scene = sceneList.get(i);
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.scene_item,null);
            holder = new ViewHolder();
            holder.scene = (TextView) view.findViewById(R.id.scene_name);
            view.setTag(holder);
        }else
            holder = (ViewHolder) view.getTag();
        holder.scene.setText(scene.getSceneName());
        holder.scene.setTag(R.id.identity,scene.getSceneID());
        holder.scene.setTag(R.id.state,scene.getOpen()?1:0);
        return view;
    }


    class ViewHolder{
        TextView scene;
    }

}

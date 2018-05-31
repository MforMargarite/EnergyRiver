package com.whucs.energyriver.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.whucs.energyriver.Bean.Scene;
import com.whucs.energyriver.R;
import com.whucs.energyriver.Widget.SceneView;

import java.util.ArrayList;
import java.util.List;


public class SceneAdapter extends BaseAdapter{
    private Context context;
    private List<Scene>sceneList;

    public SceneAdapter(Context context, List<Scene> sceneList) {
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
            view = new SceneView(context);
            holder = new ViewHolder();
            holder.scene = (SceneView) view;
            view.setTag(holder);
        }else
            holder = (ViewHolder) view.getTag();
        holder.scene.setId(R.id.scenes);
        holder.scene.setSceneInfo(scene.getSceneName(),scene.getOpen());
        holder.scene.setTag(R.id.identity,scene.getSceneID());
        holder.scene.setTag(R.id.scene_name,scene.getSceneName());
        holder.scene.setTag(R.id.state,scene.getOpen()?1:0);
        return view;
    }

    class ViewHolder{
        SceneView scene;
    }

}

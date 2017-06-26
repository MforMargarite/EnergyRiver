package com.whucs.energyriver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


public class ControlFragment extends Fragment implements View.OnClickListener{
    ImageView menu,add_scene;
    LinearLayout room_info;
    GridView scene_info;
    ListView loop_ListView;
    TextView room;
    Activity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.control,null);
        initWidget(view);
        return view;
    }

    private void initWidget(View view){
        activity = getActivity();
        menu = (ImageView) view.findViewById(R.id.menu);
        room = (TextView) view.findViewById(R.id.room);
        add_scene = (ImageView) view.findViewById(R.id.add_scene);
        room_info = (LinearLayout) view.findViewById(R.id.room_info);
        scene_info = (GridView) view.findViewById(R.id.scene_info);
        loop_ListView = (ListView) view.findViewById(R.id.loop_listView);

        menu.setOnClickListener(this);
        add_scene.setOnClickListener(this);
        room_info.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.menu:
                intent = new Intent(activity,SceneActivity.class);
                startActivity(intent);
                break;
            case R.id.add_scene:
                intent = new Intent(activity,AddSceneActivity.class);
                startActivity(intent);
                break;
            case R.id.room_info:
                intent = new Intent(activity,ChooseRoomActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}

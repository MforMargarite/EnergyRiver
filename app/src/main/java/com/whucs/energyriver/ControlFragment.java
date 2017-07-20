package com.whucs.energyriver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.whucs.energyriver.Adapter.LoopAdapter;
import com.whucs.energyriver.Bean.Building;
import com.whucs.energyriver.Bean.Loop;
import com.whucs.energyriver.Presenter.ControlPresenter;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.View.ControlView;
import com.whucs.energyriver.Widget.StateSwitchFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class ControlFragment extends StateSwitchFragment implements View.OnClickListener,ControlView {
    ImageView menu,add_scene;
    LinearLayout room_info;
    GridView scene_info;
    ListView loopListView;
    TextView room;
    Activity activity;
  //  ProgressDialog dialog;
    Long buildingID = 79L;
    ControlPresenter controlPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);
        View content = inflater.inflate(R.layout.control,null);
        initWidget(content);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            controlPresenter = new ControlPresenter(this);
            controlPresenter.getFirstBuildUnit(activity);
        }
    }

    @Override
    public void reload(){
        controlPresenter.getFirstBuildUnit(activity);
    }

    private void initWidget(View view){
        iniAdapter(view);
        activity = getActivity();
        menu = (ImageView) view.findViewById(R.id.menu);
        room = (TextView) view.findViewById(R.id.room);
        add_scene = (ImageView) view.findViewById(R.id.add_scene);
        room_info = (LinearLayout) view.findViewById(R.id.room_info);
        scene_info = (GridView) view.findViewById(R.id.scene_info);
        loopListView = (ListView) view.findViewById(R.id.loop_listView);

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

    @Override
    public void showWaiting() {
        showLoading();
    }


    @Override
    public Long getBuildingID() {
        return buildingID;
    }

    @Override
    public void setLoopList(List<Loop> loops) {
        LoopAdapter loopAdapter = new LoopAdapter(activity, loops);
        loopListView.setAdapter(loopAdapter);
        loopAdapter.notifyDataSetInvalidated();
        showContent();
    }

    @Override
    public void setBuildingUnit(Building building) {
        buildingID = building.getBuildingID();
        room.setText(building.getBuildingName());
        controlPresenter.getLoopInfoByBuildID(activity);
        showContent();
    }

    @Override
    public void execError(String msg) {
        showError();
        Toast.makeText(activity,msg,Toast.LENGTH_SHORT).show();
    }
}

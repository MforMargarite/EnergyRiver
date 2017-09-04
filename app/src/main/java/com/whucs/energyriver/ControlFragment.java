package com.whucs.energyriver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.google.gson.Gson;
import com.whucs.energyriver.Adapter.LoopAdapter;
import com.whucs.energyriver.Bean.ACAlter;
import com.whucs.energyriver.Bean.ACCollect;
import com.whucs.energyriver.Bean.Building;
import com.whucs.energyriver.Bean.Loop;
import com.whucs.energyriver.Presenter.ControlPresenter;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.View.ControlView;
import com.whucs.energyriver.Widget.AirControlDialog;
import com.whucs.energyriver.Widget.ScrollListView;
import com.whucs.energyriver.Widget.StateSwitchFragment;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ControlFragment extends StateSwitchFragment implements View.OnClickListener,ControlView {
    View view ;
    ImageView menu,add_scene;
    LinearLayout room_info;
    GridView scene_info;
    ListView loopListView;
    TextView room;
    MainActivity activity;

    ProgressDialog waiting;
    AirControlDialog dialog;
    EditText setTemp;
    ACAlter acAlter;
    ACCollect acCollect;
    TextView curSpeedView = null;
    TextView curModeView = null;

    Long buildingID,loopID;
    String buildingName;
    String loopState;
    ControlPresenter controlPresenter;
    Resources res;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = super.onCreateView(inflater,container,savedInstanceState);
        View content = inflater.inflate(R.layout.control,null);
        initWidget(content);
        return view;
    }

    private void getPageInfo(){
        if(controlPresenter == null)
            controlPresenter = new ControlPresenter(this);
        buildingID = Common.getSharedPreference(activity).getLong("buildingID",-1L);
        if(buildingID!=-1L) {
            buildingName = Common.getSharedPreference(activity).getString("buildingName","");
            room.setText(buildingName);
            controlPresenter.getLoopInfoByBuildID(activity);
        }else
            controlPresenter.getFirstBuildUnit(activity);
    }

    @Override
    public void reload(){
        getPageInfo();
    }

    private void initWidget(View view){
        activity = (MainActivity) getActivity();
        //调用父类装入View content的方法,并加入权限页
        iniAdapter(view);
        addState("auth",getAuthView());
        //判断是否有权限使用控制功能
        if(!Common.hasAuth(activity))
            showViewByTag("auth");
        else {
            menu = (ImageView) view.findViewById(R.id.menu);
            room = (TextView) view.findViewById(R.id.room);
            add_scene = (ImageView) view.findViewById(R.id.add_scene);
            room_info = (LinearLayout) view.findViewById(R.id.room_info);
            scene_info = (GridView) view.findViewById(R.id.scene_info);
            loopListView = (ListView) view.findViewById(R.id.loop_listView);
            acAlter = new ACAlter();
            res = activity.getResources();
            menu.setOnClickListener(this);
            add_scene.setOnClickListener(this);
            room_info.setOnClickListener(this);
            getPageInfo();
        }
    }

    private View getAuthView(){
        //获取View并初始化
        View view = LayoutInflater.from(activity).inflate(R.layout.authority_check,null);
        TextView auth_cancel = (TextView) view.findViewById(R.id.auth_cancel);
        TextView auth_ensure = (TextView) view.findViewById(R.id.auth_ensure);
        auth_cancel.setOnClickListener(this);
        auth_ensure.setOnClickListener(this);

        List<Loop>loops = new ArrayList<>();
        Gson gson = new Gson();
        for (String loop:Common.loops) {
            loops.add(gson.fromJson(loop,Loop.class));
        }
        ScrollListView loopListView = (ScrollListView) view.findViewById(R.id.loop_listView);
        LoopAdapter loopAdapter = new LoopAdapter(activity,loops,this);
        loopListView.setAdapter(loopAdapter);
        loopAdapter.notifyDataSetInvalidated();
        return view;
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
                startActivityForResult(intent,0);
                break;
            case R.id.toggle:
                //空调控制 显示面板
                loopID = Long.parseLong(view.getTag().toString());
                controlPresenter.getAirState(activity,loopID);
                break;
            case R.id.switcher:
                ToggleButton switcher = (ToggleButton)view;
                loopID = Long.parseLong(switcher.getTag().toString());
                loopState = "{'EquipmentStatus':"+(switcher.isChecked()?1:0)+"}";
                controlPresenter.updateLoopState(switcher,activity);
                break;
            case R.id.to_low:
                acAlter.setSpeed(0);
                changeAirSpeedTo(view);
                break;
            case R.id.to_medium:
                acAlter.setSpeed(1);
                changeAirSpeedTo(view);
                break;
            case R.id.to_high:
                acAlter.setSpeed(2);
                changeAirSpeedTo(view);
                break;
            case R.id.to_cold:
                acAlter.setMode(0);
                changeAirModeTo(view);
                break;
            case R.id.to_hot:
                acAlter.setMode(1);
                changeAirModeTo(view);
                break;
            case R.id.to_dehumidify:
                acAlter.setMode(2);
                changeAirModeTo(view);
                break;
            case R.id.air_switch:
                acAlter.setEquipmentStatus(1-acAlter.getEquipmentStatus());
                changeAirSwitchTo(view,1-acAlter.getEquipmentStatus());
                break;
            case R.id.ensure:
                acAlter.setSetTemp(Float.parseFloat(setTemp.getText().toString()));
                loopState = new Gson().toJson(acAlter);
                controlPresenter.updateLoopState(view,activity);
                acAlter = new ACAlter();
                break;
            case R.id.cancel:
                if(dialog!=null)
                    dialog.dismiss();
                break;
            case R.id.auth_ensure:
                //跳转至缴费页面
                intent = new Intent(activity,BillActivity.class);
                startActivity(intent);
                break;
            case R.id.auth_cancel:
                //跳转至能耗概况tab
                activity.setCurrentTab(0);
                break;
            default:
                break;
        }
    }

    private void changeAirSwitchTo(View view,int openStatus){
        ImageView air_switch = (ImageView)view;
        if(openStatus>0){
            air_switch.setImageDrawable(res.getDrawable(R.mipmap.air_on));
        }else
            air_switch.setImageDrawable(res.getDrawable(R.mipmap.air_off));
    }

    private void changeAirSpeedTo(View view){
        TextView status = (TextView)view;
        status.setTextColor(res.getColor(R.color.control_selected));
        if(curSpeedView != null){
            curSpeedView.setTextColor(res.getColor(R.color.control_name));
        }
        curSpeedView = status;
    }

    private void changeAirModeTo(View view){
        TextView status = (TextView)view;
        status.setTextColor(res.getColor(R.color.control_selected));
        if(curModeView != null){
            curModeView.setTextColor(res.getColor(R.color.control_name));
        }
        curModeView = status;
    }


    private void initDialog(final ACCollect acCollect){
        dialog.setContentView(R.layout.air_control);
        TextView ensure = (TextView) dialog.findViewById(R.id.ensure);
        TextView cancel = (TextView) dialog.findViewById(R.id.cancel);
        ensure.setOnClickListener(this);
        cancel.setOnClickListener(this);

        setTemp = (EditText) dialog.findViewById(R.id.input_set_temp);//设定温度
        TextView roomTemp = (TextView) dialog.findViewById(R.id.room_temp_value);//室内温度
        ImageView air_switch = (ImageView) dialog.findViewById(R.id.air_switch);
        TextView low_speed = (TextView) dialog.findViewById(R.id.to_low);
        TextView medium_speed = (TextView) dialog.findViewById(R.id.to_medium);
        TextView high_speed = (TextView) dialog.findViewById(R.id.to_high);
        TextView cold_mode = (TextView) dialog.findViewById(R.id.to_cold);
        TextView hot_mode = (TextView) dialog.findViewById(R.id.to_hot);
        TextView dehumidify_mode = (TextView) dialog.findViewById(R.id.to_dehumidify);
        switch (acCollect.getMode()){
            case 0:
                changeAirModeTo(cold_mode);
                break;
            case 1:
                changeAirModeTo(hot_mode);
                break;
            case 2:
                changeAirModeTo(dehumidify_mode);
                break;
        }
        switch (acCollect.getSpeed()){
            case 0:
                changeAirSpeedTo(low_speed);
                break;
            case 1:
                changeAirSpeedTo(medium_speed);
                break;
            case 2:
                changeAirSpeedTo(high_speed);
                break;
        }
        acAlter.setSetTemp(Float.parseFloat(acCollect.getSetTemp()));
        acAlter.setSpeed(acCollect.getSpeed());
        acAlter.setMode(acCollect.getMode());
        acAlter.setEquipmentStatus(acCollect.getEquipmentStatus());
        changeAirSwitchTo(air_switch,acAlter.getEquipmentStatus());
        roomTemp.setText(Math.round(Float.parseFloat(acCollect.getRoomTemp()))+" ℃");
        setTemp.setText(Math.round(Float.parseFloat(acCollect.getSetTemp()))+"");
        low_speed.setOnClickListener(this);
        medium_speed.setOnClickListener(this);
        high_speed.setOnClickListener(this);
        cold_mode.setOnClickListener(this);
        hot_mode.setOnClickListener(this);
        dehumidify_mode.setOnClickListener(this);
        air_switch.setOnClickListener(this);
    }

    @Override
    public void showLoading() {
        super.showViewByTag("loading");
    }

    @Override
    public void showWaiting() {
        if(waiting == null){
            waiting = new ProgressDialog(activity);
        }
        waiting.show();
        //  waiting.setContentView();

    }

    @Override
    public void hideWaiting() {
        if(waiting.isShowing())
            waiting.dismiss();
    }

    @Override
    public Long getBuildingID() {
        return buildingID;
    }

    @Override
    public Long getLoopID() {
        return loopID;
    }

    @Override
    public String getLoopState() {
        try {
            loopState = URLEncoder.encode(loopState, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loopState;
    }

    @Override
    public void setLoopList(List<Loop> loops) {
        LoopAdapter loopAdapter = new LoopAdapter(activity,loops,this);
        loopListView.setAdapter(loopAdapter);
        loopAdapter.notifyDataSetInvalidated();
        showViewByTag("content");
    }

    @Override
    public void setBuildingUnit(Building building) {
        buildingID = building.getBuildingID();
        room.setText(building.getBuildingName());
        controlPresenter.getLoopInfoByBuildID(activity);
        controlPresenter.getLoopStateByBuild(activity);
        showViewByTag("content");
    }

    @Override
    public void execError(String msg) {
        showViewByTag("error");
        Toast.makeText(activity,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUpdateResult(View view,Boolean updated) {
        if(!updated){
            switch (view.getId()){
                case R.id.switcher:
                    ToggleButton switcher = (ToggleButton)view;
                    switcher.setChecked(!switcher.isChecked());
                    Toast.makeText(activity,"状态更新失败",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }else
            Toast.makeText(activity,"更新成功",Toast.LENGTH_SHORT).show();
        if(dialog!=null)
            if(dialog.isShowing())
                dialog.dismiss();
    }

    @Override
    public void updateError(View view) {
        switch (view.getId()){
            case R.id.switcher:
                ToggleButton switcher = (ToggleButton)view;
                switcher.setChecked(!switcher.isChecked());
                break;
            default:
                break;
        }
        Toast.makeText(activity,"更新失败,请检查网络后重试",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setLoopState(HashMap<Long, Object> map) {
        Iterator<Map.Entry<Long,Object>>it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Long,Object>entry = it.next();
            ToggleButton switcher = (ToggleButton) view.findViewWithTag(entry.getKey());
            int status = Integer.parseInt(entry.getValue().toString());
            switcher.setChecked(status>0);
        }
    }

    @Override
    public void getStateError() {
        Toast.makeText(activity,"获取回路状态失败",Toast.LENGTH_SHORT).show();
        //所有按钮不可点击
    }

    @Override
    public void setACCollect(ACCollect acCollect) {
        this.acCollect = acCollect;
        dialog = new AirControlDialog(activity);
        dialog.setView(new EditText(activity));
        dialog.show();
        initDialog(acCollect);
    }

    @Override
    public void getACError() {
        Toast.makeText(activity,"获取空调状态失败,暂不允许控制,请检查网络后重试",Toast.LENGTH_SHORT).show();
        if(dialog!=null && dialog.isShowing())
            dialog.dismiss();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case 0://选择房间
                if(resultCode == 1) {
                    //选择了数据
                    Long newBuildingID = data.getLongExtra("buildingID", buildingID);
                    String newBuildingName = data.getStringExtra("buildingName");
                    //todo 保存至SharedPreference
                    Common.saveBuilding(activity,newBuildingID,newBuildingName);
                    if (newBuildingID != buildingID) {
                        buildingID = newBuildingID;
                        room.setText(newBuildingName);
                    }
                    controlPresenter.getLoopInfoByBuildID(activity);
                }
                break;
        }
    }

}

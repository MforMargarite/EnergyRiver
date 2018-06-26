package com.whucs.energyriver;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.google.gson.Gson;
import com.whucs.energyriver.Adapter.LoopAdapter;
import com.whucs.energyriver.Adapter.SceneAdapter;
import com.whucs.energyriver.Bean.ACAlter;
import com.whucs.energyriver.Bean.ACCollect;
import com.whucs.energyriver.Bean.LoopStatus;
import com.whucs.energyriver.Bean.Scene;
import com.whucs.energyriver.Presenter.ControlPresenter;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.View.ControlView;
import com.whucs.energyriver.Widget.AirControlDialog;
import com.whucs.energyriver.Widget.ScrollGridView;
import com.whucs.energyriver.Widget.ScrollListView;
import com.whucs.energyriver.Widget.StateSwitchFragment;
import java.util.ArrayList;
import java.util.List;


public class ControlFragment extends StateSwitchFragment implements View.OnClickListener,ControlView,AdapterView.OnItemClickListener,View.OnTouchListener {
    View view;
    MainActivity activity;
    Resources res;
    TextView title;
    ScrollView wrapper;
    ImageView scene_edit;
    ScrollGridView scene_info;
    ImageView group_edit;
    ScrollGridView group_control;
    ScrollListView loopListView;

    SceneAdapter sceneAdapter,groupControlAdapter;
    LoopAdapter loopAdapter;
    List<Scene>sceneList,groupControlList;
    List<LoopStatus>loopList;
    ControlPresenter controlPresenter;

    //空调智能控制 组件
    AirControlDialog dialog;
    EditText setTemp;
    TextView curSpeedView = null;
    TextView curModeView = null;
    ACAlter acAlter;

    //下拉刷新参数
    private float startY;
    private boolean isShowing = false;
    private boolean readyForRefresh = true;
    private boolean pullToRefresh = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = super.onCreateView(inflater,container,savedInstanceState);
        View content = inflater.inflate(R.layout.control,null);
        initWidget(content);
        return view;
    }

    //加载页面：获取场景、群组和所有回路
    private void getPageInfo(){
        if(controlPresenter == null)
            controlPresenter = new ControlPresenter(this);
        controlPresenter.getScenes(activity);
        controlPresenter.getGroupControls(activity);
        controlPresenter.getLoopByUser(activity);
    }

    @Override
    public void reload(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getPageInfo();
            }
        },1000);
    }

    private void initWidget(View view){
        activity = (MainActivity) getActivity();
        //调用父类装入View content的方法,并加入权限页
        iniAdapter(view);
        //判断是否有权限使用控制功能
        if(!Common.hasAuth(activity)) {
            addState("auth",getAuthView());
            showViewByTag("auth");
        }
        else {
            acAlter = new ACAlter();
            res = activity.getResources();
            //场景
            int widthSlice = Common.getParentWidth(activity)/9;
            int heightSlice = Common.getParentHeight(activity)/40;
            scene_info = (ScrollGridView) view.findViewById(R.id.scene_info);
            scene_info.setPadding(widthSlice,heightSlice,widthSlice,heightSlice);
            scene_info.setHorizontalSpacing(widthSlice);
            scene_edit = (ImageView) view.findViewById(R.id.scene_edit);
            scene_edit.setOnClickListener(this);
            //组控
            group_control = (ScrollGridView) view.findViewById(R.id.group_control);
            group_control.setPadding(widthSlice,heightSlice,widthSlice,heightSlice);
            group_control.setHorizontalSpacing(widthSlice);
            group_edit = (ImageView) view.findViewById(R.id.group_edit);
            group_edit.setOnClickListener(this);
            //回路列表
            loopListView = (ScrollListView) view.findViewById(R.id.loop_listView);
            wrapper = (ScrollView) view.findViewById(R.id.wrapper);
            wrapper.setOnTouchListener(this);
            //加载数据
            getPageInfo();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            if(!Common.hasAuth(activity)) {
                activity.setToolbar(View.GONE);
            }else{
                activity.setToolbar(View.VISIBLE);
            }
            activity.setMenuState(false);
        }
    }

    private View getAuthView(){
        //获取View并初始化
        View view = LayoutInflater.from(activity).inflate(R.layout.authority_check,null);
        TextView auth_cancel = (TextView) view.findViewById(R.id.auth_cancel);
        TextView auth_ensure = (TextView) view.findViewById(R.id.auth_ensure);
        auth_cancel.setOnClickListener(this);
        auth_ensure.setOnClickListener(this);

        List<LoopStatus>loops = new ArrayList<>();
        Gson gson = new Gson();
        for (String loop:Common.loops) {
            loops.add(gson.fromJson(loop,LoopStatus.class));
        }
        ScrollListView loopListView = (ScrollListView) view.findViewById(R.id.loop_listView);
        LoopAdapter loopAdapter = new LoopAdapter(activity,loops,this);
        loopListView.setAdapter(loopAdapter);
        loopAdapter.notifyDataSetInvalidated();
        return view;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch(motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(pullToRefresh)
                    return true;
                startY = motionEvent.getY();
                readyForRefresh = isTop(wrapper);
                break;
            case MotionEvent.ACTION_MOVE:
                if(pullToRefresh)
                    return true;
                else if(readyForRefresh) {//已经滑动到顶部
                    float dist = motionEvent.getY() - startY;
                    if(dist>0) {
                        if (dist > 216) {
                            activity.showPullToRefresh(216);
                            pullToRefresh = true;
                        } else {
                            activity.showPullToRefresh((int) dist);
                            isShowing = true;
                        }
                        return true;
                    }else{
                        if(isShowing)
                            return true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if(pullToRefresh) {
                    reload();
                    activity.setRefreshState("正在刷新");
                }else if(isShowing) {
                    activity.hidePullToRefresh();
                    isShowing = false;
                }
                break;
        }
        return false;
    }

    private boolean isTop(ScrollView wrapper){
        return wrapper.getScrollY() == 0;
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.toggle:
                //空调控制 显示面板
              //  controlPresenter.getAirState(activity,loopID);
                break;
            case R.id.switcher:
                ToggleButton switcher = (ToggleButton)view;
                switcher.setTag(R.id.state,switcher.isChecked()?1:0);
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
                //loopState = new Gson().toJson(acAlter);
                controlPresenter.updateLoopState(view,activity);
                acAlter.clear();
                break;
            case R.id.cancel:
                if(dialog!=null)
                    dialog.dismiss();
                break;
            case R.id.auth_ensure:
                //跳转至缴费页面
                //intent = new Intent(activity,BillActivity.class);
                // startActivity(intent);
                break;
            case R.id.auth_cancel:
                //跳转至能耗概况tab
                activity.setCurrentTab(0);
                break;
            case R.id.scene_edit:
                intent = new Intent(activity,SceneActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);
                break;
            case R.id.group_edit:
                intent = new Intent(activity,SceneActivity.class);
                intent.putExtra("type",2);
                startActivity(intent);
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
    public void showHint(String hint) {
        Toast.makeText(activity,hint,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideWaiting() {
        Log.e("what","hiding");
    }

    @Override
    public void execError(String msg) {
        showViewByTag("error");
        Toast.makeText(activity,msg,Toast.LENGTH_SHORT).show();
        activity.hidePullToRefresh();
    }


    @Override
    public void setSceneList(List<Scene> scenes) {
        if(sceneList == null)
            sceneList = new ArrayList<>();
        sceneList.clear();
        sceneList.addAll(scenes);
        if(sceneAdapter == null) {
            sceneAdapter = new SceneAdapter(activity, sceneList);
            scene_info.setAdapter(sceneAdapter);
            scene_info.setOnItemClickListener(this);
        }else
            sceneAdapter.notifyDataSetInvalidated();
    }

    @Override
    public void setGroupControlList(List<Scene> scenes) {
        if(groupControlList == null)
            groupControlList = new ArrayList<>();
        groupControlList.clear();
        groupControlList.addAll(scenes);
        if(groupControlAdapter == null) {
            groupControlAdapter = new SceneAdapter(activity, groupControlList);
            group_control.setAdapter(groupControlAdapter);
            group_control.setOnItemClickListener(this);
        }else
            groupControlAdapter.notifyDataSetInvalidated();
    }

    @Override
    public void setAllLoopList(List<LoopStatus> loops) {
        if(loopList == null)
            loopList = new ArrayList<>();
        loopList.clear();
        loopList.addAll(loops);
        if(loopAdapter == null) {
            loopAdapter = new LoopAdapter(activity, loopList, this);
            loopListView.setAdapter(loopAdapter);
        }else
            loopAdapter.notifyDataSetInvalidated();
        showViewByTag("content");
        activity.hidePullToRefresh();
        pullToRefresh = false;
    }

    @Override
    public void setUpdateResult(View view,String type,Boolean updated) {
        switch (view.getId()){
            case R.id.switcher:
                if(!updated) {
                    ToggleButton switcher = (ToggleButton) view;
                    switcher.setChecked(!switcher.isChecked());
                    Toast.makeText(activity, "状态更新失败", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(activity,"更新成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.scenes:
                if(updated) {
                    //更新成功 切换场景显示
                    if(type.equals("场景")) {
                        controlPresenter.getScenes(activity);
                        Toast.makeText(activity,"已切换至"+view.getTag(R.id.scene_name)+type+"模式",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        controlPresenter.getGroupControls(activity);
                        String openOrClose = Integer.parseInt(view.getTag(R.id.state).toString())>0?"关闭":"开启";
                        Toast.makeText(activity,"已"+openOrClose+view.getTag(R.id.scene_name)+type+"模式",Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            default:
                break;
        }
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
                Toast.makeText(activity,"更新失败,请检查网络后重试",Toast.LENGTH_SHORT).show();
                break;
            case R.id.scenes:
                Toast.makeText(activity,"更新场景或组控状态失败,请检查网络后重试",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //若需要切换场景 则执行更新状态操作
        switch (adapterView.getId()) {
            case R.id.scene_info:
                if(Integer.parseInt(view.getTag(R.id.state).toString()) == 0)
                    controlPresenter.updateScene(view, "场景", activity);
                break;
            case R.id.group_control:
                controlPresenter.updateGroupControl(view, "组控", activity);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(controlPresenter == null)
            controlPresenter = new ControlPresenter(this);
        controlPresenter.getScenes(activity);
        controlPresenter.getGroupControls(activity);
        controlPresenter.getLoopByUser(activity);
    }
}

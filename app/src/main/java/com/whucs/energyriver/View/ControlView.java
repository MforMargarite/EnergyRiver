package com.whucs.energyriver.View;

import android.view.View;

import com.whucs.energyriver.Bean.ACCollect;
import com.whucs.energyriver.Bean.Building;
import com.whucs.energyriver.Bean.Loop;

import java.util.HashMap;
import java.util.List;


public interface ControlView {
    void showWaiting();
    void showLoading();//加载页面
    void hideWaiting();//AlertDialog

    Long getBuildingID();
    Long getLoopID();
    String getLoopState();//返回json格式回路状态
    Integer getLoopOpenStatus();//返回回路状态

    void setLoopList(List<Loop> loops);//获取房间下所有回路
    void setBuildingUnit(Building building);//获取当前建筑单元(房间)
    void execError(String msg);
    void setUpdateResult(View view, Boolean updated);//获取智能控制结果
    void updateError(View view);//智能控制失败

    void setLoopState(HashMap<Long,Object> map);//获取回路状态
    void getStateError();//获取回路状态失败
    void setACCollect(ACCollect acCollect);//获取空调回路状态
    void getACError();
}

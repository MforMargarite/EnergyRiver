package com.whucs.energyriver.View;

import android.view.View;

import com.whucs.energyriver.Bean.Building;
import com.whucs.energyriver.Bean.Loop;
import java.util.List;


public interface ControlView {
    void showWaiting();
    void showLoading();//加载页面
    void hideWaiting();//AlertDialog

    Long getBuildingID();
    Long getLoopID();
    String getLoopState();//返回json格式回路状态

    void setLoopList(List<Loop> loops);//获取房间下所有回路
    void setBuildingUnit(Building building);//获取当前建筑单元(房间)
    void execError(String msg);
    void setUpdateResult(View view, Boolean updated);
    void updateError(View view);
}

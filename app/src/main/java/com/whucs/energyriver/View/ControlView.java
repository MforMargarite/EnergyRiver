package com.whucs.energyriver.View;

import com.whucs.energyriver.Bean.Building;
import com.whucs.energyriver.Bean.Loop;
import java.util.List;


public interface ControlView {
    void showWaiting();
//    void hideWaiting();

    Long getBuildingID();

    void setLoopList(List<Loop> loops);//获取房间下所有回路
    void setBuildingUnit(Building building);//获取当前建筑单元(房间)
    void execError(String msg);
}

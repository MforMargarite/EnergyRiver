package com.whucs.energyriver.View;

import com.whucs.energyriver.Bean.Building;
import com.whucs.energyriver.Bean.Loop;

import java.util.List;


public interface ChooseRoomView {
    void showWaiting();
    void hideWaiting();

    void setBuildingInfo(List<Building> buildings);//根据userID获取所属建筑
    void execError();
}

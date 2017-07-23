package com.whucs.energyriver.View;

import com.whucs.energyriver.Bean.Tree;


public interface ChooseRoomView {
    void showWaiting();
    void setBuildingInfo(Tree tree);//根据userID获取所属建筑
    void execError();
}

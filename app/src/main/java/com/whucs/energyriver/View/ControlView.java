package com.whucs.energyriver.View;

import android.view.View;

import com.whucs.energyriver.Bean.ACCollect;
import com.whucs.energyriver.Bean.Building;
import com.whucs.energyriver.Bean.Loop;
import com.whucs.energyriver.Bean.LoopStatus;
import com.whucs.energyriver.Bean.Scene;

import java.util.HashMap;
import java.util.List;


public interface ControlView {
    void showHint(String hint);
    void hideWaiting();//AlertDialog

    void setSceneList(List<Scene> scenes);
    void setGroupControlList(List<Scene> scenes);

    //返回回路+状态：userID
    void setAllLoopList(List<LoopStatus> loops);//获取用户对应的所有回路

    void setUpdateResult(View view, String type,Boolean updated);//获取控制结果
    void updateError(View view);//控制失败

    void execError(String msg);
}

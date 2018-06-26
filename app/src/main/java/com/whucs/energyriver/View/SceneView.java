package com.whucs.energyriver.View;

import com.whucs.energyriver.Bean.Scene;

import java.util.List;

public interface SceneView {
    void setSceneList(List<Scene> scenes);
    void execError();

    void delSuccess(int position);
    void delError();//提示错误
}

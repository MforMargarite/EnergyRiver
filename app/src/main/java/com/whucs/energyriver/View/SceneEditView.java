package com.whucs.energyriver.View;

import com.whucs.energyriver.Bean.LoopStatus;
import java.util.List;

public interface SceneEditView {
    void setControlLoops(List<LoopStatus> controlLoops);
    void execError(String hint);
    void postSuccess();
    void postError();
}


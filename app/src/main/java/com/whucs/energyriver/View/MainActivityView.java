package com.whucs.energyriver.View;

import com.whucs.energyriver.Bean.Building;
import com.whucs.energyriver.Bean.VersionInfo;

import java.util.List;


public interface MainActivityView {
    void getVersionInfo(VersionInfo info);
    void execError(String s);
}

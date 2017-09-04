package com.whucs.energyriver.View;

import com.whucs.energyriver.Bean.SubUser;
import java.util.List;


public interface SubUserView {
    void getSubSuccess(List<SubUser> userList);
    void execError(String msg);
}

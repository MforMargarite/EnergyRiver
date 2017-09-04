package com.whucs.energyriver.View;

import com.whucs.energyriver.Bean.SubUser;

import java.util.List;


public interface EditUserView {
    void showWaiting();
    void hideWaiting();

    SubUser getSubUser();

    void execSuccess(String msg);
    void execError(String msg);

}

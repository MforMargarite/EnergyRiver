package com.whucs.energyriver.View;



public interface ChangePwdView {
    void showWaiting();
    void hideWaiting();

    String getUsername();
    String getPwd();
    String getNewPwd();

    void changePwdSuccess();
    void execError(String msg);
}

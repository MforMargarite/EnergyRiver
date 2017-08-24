package com.whucs.energyriver.View;



public interface UserInfoView {
    void showWaiting();
    void hideWaiting();

    byte[] getAvatarData();
    String getUsername();

    void uploadAvatarSuccess(byte[] avatar_byte);
    void uploadUsernameSuccess(String username);
    void execError(String msg);
}

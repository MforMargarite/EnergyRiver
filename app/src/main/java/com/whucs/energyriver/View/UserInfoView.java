package com.whucs.energyriver.View;



public interface UserInfoView {
    void showWaiting();
    void hideWaiting();

    byte[] getAvatarData();
    String getUsername();
    String getAvatarURL();

    void uploadAvatarSuccess(String url);
    void updateURLSuccess(byte[] avatar_byte);
    void uploadUsernameSuccess(String username);
    void execError(String msg);
}

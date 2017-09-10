package com.whucs.energyriver.View;



public interface UserInfoView {
    void showWaiting();
    void hideWaiting();

    byte[] getAvatarData();
    String getUsername();
    String getAvatarURL();
    String getMobile();


    void changeMobileSuccess();
    void uploadAvatarSuccess(String url);
    void updateURLSuccess(byte[] avatar_byte);
    void uploadUsernameSuccess();
    void execError(String msg);
}

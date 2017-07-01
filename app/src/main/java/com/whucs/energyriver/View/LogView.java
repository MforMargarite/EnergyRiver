package com.whucs.energyriver.View;

import com.whucs.energyriver.Bean.User;


public interface LogView {
    void showWaiting();
    void hideWaiting();

    String getUsername();
    String getPassword();

    void setUser(User user);
    void loginError(String msg);
}

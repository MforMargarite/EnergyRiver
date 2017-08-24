package com.whucs.energyriver.Bean;


public class LoginInfo {
    private Long userID;
    private String username;
    private String tokenNo;


    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", tokenNo='" + tokenNo + '\'' +
                '}';
    }
}

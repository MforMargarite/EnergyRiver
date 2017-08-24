package com.whucs.energyriver.Bean;


public class PwdState {
    boolean IsSuccess;
    String message;

    public boolean isSuccess() {
        return IsSuccess;
    }

    public void setSuccess(boolean success) {
        IsSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PwdState{" +
                "IsSuccess=" + IsSuccess +
                ", message='" + message + '\'' +
                '}';
    }
}

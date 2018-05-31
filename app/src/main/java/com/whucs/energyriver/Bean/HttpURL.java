package com.whucs.energyriver.Bean;

public class HttpURL<T> {
    int code;
    boolean result;
    T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpURL{" +
                "code=" + code +
                ", result=" + result +
                ", data='" + data + '\'' +
                '}';
    }
}

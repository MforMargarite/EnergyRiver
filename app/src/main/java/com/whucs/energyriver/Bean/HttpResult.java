package com.whucs.energyriver.Bean;

//返回执行结果的请求结果
public class HttpResult {
    private int code;
    private Boolean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Boolean isResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + code +
                ", result=" + result +
                '}';
    }
}

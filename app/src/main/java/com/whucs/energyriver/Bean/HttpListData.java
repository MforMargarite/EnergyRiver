package com.whucs.energyriver.Bean;

import android.util.Log;

//返回数据的请求结果
public class HttpListData<T> {
    private int code;
    private int total;
    private T data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
       // Log.e("what",data.toString());
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

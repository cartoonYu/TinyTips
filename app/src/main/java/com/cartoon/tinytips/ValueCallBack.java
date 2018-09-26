package com.cartoon.tinytips;

public interface ValueCallBack<T>{
    void onSuccess(T t);       //返回成功调用此方法
    void onFail(String msg);  //返回失败调用此方法
}

package com.cartoon.tinytips.bean;

public interface IOperateBean<T>{
    void onSuccess(T t);
    void onFail(String msg);
}

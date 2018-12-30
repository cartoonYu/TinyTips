package com.cartoon.tinytips.util.network;

public interface IDataCallBack<T>{
    void onSuccess(T t);
    void onFail(String msg);
}

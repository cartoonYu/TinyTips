package com.cartoon.tinytips;

/**
 * Created by cartoon on 2018/2/1.
 * 1.提供接口供Model返回数据给Presenter
 * 2.具体到某个Presenter可以自定义子接口
 */

public interface ValueCallBack<T> {
    void onSuccess(T t);       //返回成功调用此方法
    void onFail(String code);  //返回失败调用此方法
}

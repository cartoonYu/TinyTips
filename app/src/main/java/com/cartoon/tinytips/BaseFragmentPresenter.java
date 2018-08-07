package com.cartoon.tinytips;

public abstract class BaseFragmentPresenter<T extends BaseFragment>{
    protected abstract void deleteView();     //解除view与presenter的连接
}

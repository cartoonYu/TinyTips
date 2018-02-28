package com.cartoon.tinytips.Main;

import com.cartoon.tinytips.BaseActivityPresenter;

/**
 * Created by cartoon on 2018/2/28.
 * 功能
 * 1.为主活动（Main）以及数据库提供中间处理层
 *
 * 操作：
 * 1.定义函数并在View进行调用，利用成员变量view操作View层的函数，实现了View与Presenter的双向调用
 * 2.MainPresenter所有函数应到IMain.Presenter定义再在此重写方法
 */

class MainPresenter extends BaseActivityPresenter<Main> implements IMain.Presenter{
    private IMain.View view;
    public MainPresenter(IMain.View view){
        this.view=view;
    }
}

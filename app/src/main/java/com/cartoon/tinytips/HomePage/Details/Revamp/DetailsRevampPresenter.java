package com.cartoon.tinytips.HomePage.Details.Revamp;

import com.cartoon.tinytips.BaseActivityPresenter;

/**
 * Created by cartoon on 2018/2/14.
 * 1.修改笔记页（DetailsRevamp）的Presenter
 *
 * 功能
 * 1.为修改笔记页（DetailsRevamp）以及数据库提供中间处理层
 *
 * 操作：
 * 1.定义函数并在View进行调用，利用成员变量view操作View层的函数，实现了View与Presenter的双向调用
 * 2.HomePageDetailsRevampPresenter所有函数应到IHomePageDetailsRevamp.Presenter定义再在此重写方法
 */

class DetailsRevampPresenter extends BaseActivityPresenter<DetailsRevamp>
        implements IDetailsRevamp.Presenter {
    private IDetailsRevamp.View view;
    public DetailsRevampPresenter(IDetailsRevamp.View view){
        this.view=view;
    }
}

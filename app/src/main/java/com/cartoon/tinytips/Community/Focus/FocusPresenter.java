package com.cartoon.tinytips.Community.Focus;

import com.cartoon.tinytips.BaseFragmentPresenter;

/**
 * Created by cartoon on 2018/2/8.
 * 1.社区关注（Focus）的Presenter
 *
 * 功能
 * 1.为社区关注（Focus）以及数据库提供中间处理层
 *
 * 操作：
 * 1.定义函数并在View进行调用，利用成员变量view操作View层的函数，实现了View与Presenter的双向调用
 * 2.CommunityFocusPresenter所有函数应到ICommunityFocus.Presenter定义再在此重写方法
 */

class FocusPresenter extends BaseFragmentPresenter<Focus>
        implements IFocus.Presenter{
    private IFocus.View view;
    public FocusPresenter(IFocus.View view){
        this.view=view;
    }

}

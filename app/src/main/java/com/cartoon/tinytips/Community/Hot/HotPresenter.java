package com.cartoon.tinytips.Community.Hot;

import com.cartoon.tinytips.BaseFragmentPresenter;

/**
 * Created by cartoon on 2018/2/8.
 * 1.社区热门（Hot）的Presenter
 *
 * 功能
 * 1.为社区热门（Hot）以及数据库提供中间处理层
 *
 * 操作：
 * 1.定义函数并在View进行调用，利用成员变量view操作View层的函数，实现了View与Presenter的双向调用
 * 2.CommunityHotPresenter所有函数应到ICommunityHot.Presenter定义再在此重写方法
 */

public class HotPresenter extends BaseFragmentPresenter<Hot>
        implements IHot.Presenter{
    private IHot.View view;
    public HotPresenter(IHot.View view){
        this.view=view;
    }
}

package com.cartoon.tinytips.Community;

import com.cartoon.tinytips.BaseFragmentPresenter;

/**
 * Created by cartoon on 2018/2/28.
 * 功能
 * 1.为社区（Community）以及数据库提供中间处理层
 *
 * 操作：
 * 1.定义函数并在View进行调用，利用成员变量view操作View层的函数，实现了View与Presenter的双向调用
 * 2.CommunityPresenter所有函数应到ICommunity.Presenter定义再在此重写方法
 */

class CommunityPresenter extends BaseFragmentPresenter<Community>
        implements ICommunity.Presenter{
    private ICommunity.View view;
    public CommunityPresenter(ICommunity.View view){
        this.view=view;
    }
    @Override
    public void deleteView(){
        view=null;
    }
}

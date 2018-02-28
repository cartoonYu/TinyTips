package com.cartoon.tinytips.Community.Recommend;

import com.cartoon.tinytips.BaseFragmentPresenter;

/**
 * Created by cartoon on 2018/2/8.
 * 1.社区推荐（CommunityRecommend）的Presenter
 *
 * 功能
 * 1.为社区推荐（CommunityRecommend）以及数据库提供中间处理层
 *
 * 操作：
 * 1.定义函数并在View进行调用，利用成员变量view操作View层的函数，实现了View与Presenter的双向调用
 * 2.CommunityRecommendPresenter所有函数应到ICommunityRecommend.Presenter定义再在此重写方法
 */

class CommunityRecommendPresenter extends BaseFragmentPresenter<CommunityRecommend> implements ICommunityRecommend.Presenter{
    private ICommunityRecommend.View view;
    public CommunityRecommendPresenter(ICommunityRecommend.View view){
        this.view=view;
    }
}

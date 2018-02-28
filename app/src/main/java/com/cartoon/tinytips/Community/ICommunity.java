package com.cartoon.tinytips.Community;

/**
 * Created by cartoon on 2018/2/28.
 * Community的公共接口
 * View类接入ICommunity.view，Presenter类接入ICommunity.Presenter
 * 在内部接口定义方法，在接入类中重写方法实现功能
 */

interface ICommunity {
    interface View{
        void initData();               //初始化必要的数据
        void initPager();             //对ViewPager进行必要的初始化
    }
    interface Presenter{

    }
}

package com.cartoon.tinytips.Personal;

import com.cartoon.tinytips.BaseFragmentPresenter;

/**
 * Created by cartoon on 2018/2/6.
 * 1.我的（Personal）的Presenter
 *
 * 功能
 * 1.为我的（Personal）以及数据库提供中间处理层
 *
 * 操作：
 * 1.定义函数并在View进行调用，利用成员变量view操作View层的函数，实现了View与Presenter的双向调用
 * 2.PersonalPresenter所有函数应到IPersonal.Presenter定义再在此重写方法
 */

class PersonalPresenter extends BaseFragmentPresenter<Personal> implements IPersonal.Presenter{
    private IPersonal.View view;
    public PersonalPresenter(IPersonal.View view){
        this.view=view;
    }
}

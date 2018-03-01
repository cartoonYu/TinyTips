package com.cartoon.tinytips.Personal.Security;

import com.cartoon.tinytips.BaseActivityPresenter;

/**
 * Created by cartoon on 2018/2/6.
 * 1.安全（PersonalSecurity）的Presenter
 *
 * 功能
 * 1.为安全（PersonalSecurity）以及数据库提供中间处理层
 *
 * 操作：
 * 1.定义函数并在View进行调用，利用成员变量view操作View层的函数，实现了View与Presenter的双向调用
 * 2.PersonalSecurityPresenter所有函数应到IPersonalSecurity.Presenter定义再在此重写方法
 */

class PersonalSecurityPresenter extends BaseActivityPresenter<PersonalSecurity>
        implements ISecurity.Presenter{
    private ISecurity.View view;
    public PersonalSecurityPresenter(ISecurity.View view){
        this.view=view;
    }
}

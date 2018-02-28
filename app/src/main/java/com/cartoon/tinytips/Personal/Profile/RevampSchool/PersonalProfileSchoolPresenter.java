package com.cartoon.tinytips.Personal.Profile.RevampSchool;

import com.cartoon.tinytips.BaseActivityPresenter;

/**
 * Created by cartoon on 2018/2/22.
 * 1.为个人资料高校修改页（PersonalProfileSchool）以及数据库提供中间处理层
 *
 * 操作：
 * 1.定义函数并在View进行调用，利用成员变量view操作View层的函数，实现了View与Presenter的双向调用
 * 2.PersonalProfileSchoolPresenter所有函数应到IPersonalProfileSchool.Presenter定义再在此重写方法
 */

class PersonalProfileSchoolPresenter extends BaseActivityPresenter<PersonalProfileSchool>
        implements IPersonalProfileSchool.Presenter{
    private IPersonalProfileSchool.View view;
    public PersonalProfileSchoolPresenter(IPersonalProfileSchool.View view){
        this.view=view;
    }
}

package com.cartoon.tinytips.Personal.Profile.RevampResume;

import com.cartoon.tinytips.BaseActivityPresenter;

/**
 * Created by cartoon on 2018/2/22.
 * 1.为个人资料简介修改页（PersonalProfileResume）以及数据库提供中间处理层
 *
 * 操作：
 * 1.定义函数并在View进行调用，利用成员变量view操作View层的函数，实现了View与Presenter的双向调用
 * 2.PersonalProfileResumePresenter所有函数应到IPersonalProfileResume.Presenter定义再在此重写方法
 */

class PersonalProfileResumePresenter extends BaseActivityPresenter<PersonalProfileResume>
        implements IPersonalProfileResume.Presenter {
    private IPersonalProfileResume.View view;
    public PersonalProfileResumePresenter(IPersonalProfileResume.View view){
        this.view=view;
    }
}

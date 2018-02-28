package com.cartoon.tinytips.Personal.Profile.RevampSignature;

import com.cartoon.tinytips.BaseActivityPresenter;

/**
 * Created by cartoon on 2018/2/22.
 *  1.为个人资料签名修改页（PersonalProfileSignature）以及数据库提供中间处理层
 *
 * 操作：
 * 1.定义函数并在View进行调用，利用成员变量view操作View层的函数，实现了View与Presenter的双向调用
 * 2.PersonalProfileSignaturePresenter所有函数应到IPersonalProfileSignature.Presenter定义再在此重写方法
 */

class PersonalProfileSignaturePresenter extends BaseActivityPresenter<PersonalProfileSignature>
        implements IPersonalProfileSignature.Presenter{
    private IPersonalProfileSignature.View view;
    public PersonalProfileSignaturePresenter(IPersonalProfileSignature.View view){
        this.view=view;
    }
}

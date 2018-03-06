package com.cartoon.tinytips.Personal;

import android.util.Log;

import com.cartoon.tinytips.BaseFragmentPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.data.TablePersonalInformation.PersonalInformation;

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

class PersonalPresenter extends BaseFragmentPresenter<Personal> implements
        IPersonal.Presenter{

    private IPersonal.View view;
    private IPersonal.Model model;

    private PersonalInformation information;

    public PersonalPresenter(IPersonal.View view){
        this.view=view;
        this.model=new PersonalModel();
    }
    @Override
    public void initData(){
        model.getPersonalInformation(new ValueCallBack<PersonalInformation>() {
            @Override
            public void onSuccess(PersonalInformation personalInformation) {
                view.initHeadPortrait(personalInformation.getHeadPortrait());
                view.initNickName(personalInformation.getNickName());
                view.initSignature(personalInformation.getSignature());
                information=personalInformation;
            }
            @Override
            public void onFail(String code) {
                view.showToast(code);
            }
        });
    }
}

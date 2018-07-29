package com.cartoon.tinytips.Personal.Profile;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.data.PersonalInformation;

/**
 * Created by cartoon on 2018/2/6.
 * 1.个人资料（Profile）的Presenter
 *
 * 功能
 * 1.为个人资料（Profile）以及数据库提供中间处理层
 *
 * 操作：
 * 1.定义函数并在View进行调用，利用成员变量view操作View层的函数，实现了View与Presenter的双向调用
 * 2.PersonalProfilePresenter所有函数应到IPersonalProfile.Presenter定义再在此重写方法
 */

class ProfilePresenter extends BaseActivityPresenter<Profile>
        implements IProfile.Presenter{

    private IProfile.View view;
    private IProfile.Model model;

    public ProfilePresenter(IProfile.View view){
        this.view=view;
        this.model=new ProfileModel();
    }
    @Override
    public void initData(String nickName){
        model.setNickName(nickName);
        model.initData(new ValueCallBack<PersonalInformation>() {
            @Override
            public void onSuccess(PersonalInformation personalInformation) {
                view.initData(personalInformation);
            }

            @Override
            public void onFail(String code) {
                view.showToast(code);
            }
        });
    }
    @Override
    public void deleteView(){
        view=null;
        model=null;
    }
}
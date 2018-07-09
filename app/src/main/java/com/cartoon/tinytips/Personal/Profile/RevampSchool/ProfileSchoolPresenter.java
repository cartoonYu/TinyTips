package com.cartoon.tinytips.Personal.Profile.RevampSchool;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;

/**
 * Created by cartoon on 2018/2/22.
 * 1.为个人资料高校修改页（ProfileSchool）以及数据库提供中间处理层
 *
 * 操作：
 * 1.定义函数并在View进行调用，利用成员变量view操作View层的函数，实现了View与Presenter的双向调用
 * 2.PersonalProfileSchoolPresenter所有函数应到IPersonalProfileSchool.Presenter定义再在此重写方法
 */

class ProfileSchoolPresenter extends BaseActivityPresenter<ProfileSchool>
        implements IProfileSchool.Presenter{
    private IProfileSchool.View view;
    private IProfileSchool.Model model;
    public ProfileSchoolPresenter(IProfileSchool.View view){
        this.view=view;
        this.model=new ProfileSchoolModel();
    }
    @Override
    public void revampSchool(){
        model.setNickName(view.getNickName());
        model.setNewSchool(view.getNewSchool());
        model.revampSchool(new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                view.showToast(s);
                view.handleClickBack();
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

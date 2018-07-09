package com.cartoon.tinytips.Personal.Profile.RevampResume;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;

/**
 * Created by cartoon on 2018/2/22.
 * 1.为个人资料简介修改页（ProfileResume）以及数据库提供中间处理层
 *
 * 操作：
 * 1.定义函数并在View进行调用，利用成员变量view操作View层的函数，实现了View与Presenter的双向调用
 * 2.PersonalProfileResumePresenter所有函数应到IPersonalProfileResume.Presenter定义再在此重写方法
 */

class ProfileResumePresenter extends BaseActivityPresenter<ProfileResume>
        implements IProfileResume.Presenter {

    private IProfileResume.View view;
    private IProfileResume.Model model;
    public ProfileResumePresenter(IProfileResume.View view){
        this.view=view;
        this.model=new ProfileResumeModel();
    }
    @Override
    public void revampResume(){
        model.setNickName(view.getNickName());
        model.setNewResume(view.getResume());
        model.revampResume(new ValueCallBack<String>() {
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

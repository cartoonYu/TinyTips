package com.cartoon.tinytips.Personal.Security;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;

/**
 * Created by cartoon on 2018/2/6.
 * 1.安全（Security）的Presenter
 *
 * 功能
 * 1.为安全（Security）以及数据库提供中间处理层
 *
 * 操作：
 * 1.定义函数并在View进行调用，利用成员变量view操作View层的函数，实现了View与Presenter的双向调用
 * 2.PersonalSecurityPresenter所有函数应到IPersonalSecurity.Presenter定义再在此重写方法
 */

class SecurityPresenter extends BaseActivityPresenter<Security>
        implements ISecurity.Presenter{
    private ISecurity.View view;
    private ISecurity.Model model;
    public SecurityPresenter(ISecurity.View view){
        this.view=view;
        this.model=new SecurityModel();
    }
    @Override
    public void revampPassword(){
        if(view.getConfirmPassword().equals(view.getNewPassword())){
            model.setNewPassword(view.getNewPassword());
            model.setOldPassword(view.getOldPassword());
            model.revampPassword(new ValueCallBack<String>() {
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
        else{
            view.showToast("两次密码输入不一致，请重试");
        }
    }
    @Override
    public void deleteView(){
        view=null;
        model=null;
    }
}

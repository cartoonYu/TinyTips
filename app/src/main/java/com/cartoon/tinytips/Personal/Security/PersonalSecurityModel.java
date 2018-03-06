package com.cartoon.tinytips.Personal.Security;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.data.TablePersonalInformation.PersonalInformation;
import com.cartoon.tinytips.util.JudgeObjectIsEmpty;

/**
 * Created by cartoon on 2018/3/6.
 * 旧密码在成员变量information，新密码为成员变量newPassword
 * 在成员函数revampPassword中以information中的nickName为查询条件更新密码
 */

class PersonalSecurityModel implements ISecurity.Model{

    private String newPassword;
    private PersonalInformation information;

    @Override
    public void setOldPassword(PersonalInformation information){
        this.information=information;
    }
    @Override
    public void setNewPassword(String newPassword){
        this.newPassword=newPassword;
    }

    @Override
    public void revampPassword(ValueCallBack<String> callBack){
        if(JudgeObjectIsEmpty.isNotEmpty(information)||JudgeObjectIsEmpty.isNotEmpty(newPassword)){
            //在这里编写数据库的逻辑
            callBack.onSuccess("修改成功");
        }
        else{
            callBack.onFail("未知错误");
        }
    }
}

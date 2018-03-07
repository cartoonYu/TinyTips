package com.cartoon.tinytips.Personal.Profile;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.data.TablePersonalInformation.PersonalInformation;
import com.cartoon.tinytips.util.JudgeObjectIsEmpty;

/**
 * Created by cartoon on 2018/3/7.
 * 在函数initData中对局部变量information进行赋值完成页面数据的初始化
 */

class PersonalProfileModel implements IProfile.Model{

    private String nickName;

    @Override
    public void setNickName(String nickName){
        this.nickName=nickName;
    }
    @Override
    public void initData(ValueCallBack<PersonalInformation> callBack){
        PersonalInformation information=new PersonalInformation();
        if(JudgeObjectIsEmpty.isNotEmpty(information)){
            callBack.onSuccess(information);
        }
        else{
            callBack.onFail("初始化信息失败，请重试");
        }
    }
}

package com.cartoon.tinytips.Personal.Profile.RevampSignature;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.util.JudgeObjectIsEmpty;

/**
 * Created by cartoon on 2018/3/7.
 * 在函数revampSignature中的if语句块中，以成员变量nickName为搜索条件，将选中项的signature替换成成员变量newSignature的值
 */

class PersonalProfileSignatureModel implements IPersonalProfileSignature.Model{

    private String nickName;
    private String newSignature;

    @Override
    public void setNickName(String nickName){
        this.nickName=nickName;
    }
    @Override
    public void setNewSignature(String newSignature){
        this.newSignature=newSignature;
    }
    @Override
    public void revampSignature(ValueCallBack<String> callBack){
        if(JudgeObjectIsEmpty.isNotEmpty(nickName)||JudgeObjectIsEmpty.isNotEmpty(newSignature)
                ||!nickName.isEmpty()){
            callBack.onSuccess("修改成功");
        }
        else{
            callBack.onFail("修改失败，请重试");
        }
    }
}

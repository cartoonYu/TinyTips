package com.cartoon.tinytips.Personal.Profile.RevampSchool;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.util.JudgeObjectIsEmpty;

/**
 * Created by cartoon on 2018/3/7.
 * 在函数revampSchool中的if语句块中，以成员变量nickName为搜索条件，将选中项的school替换成成员变量newSchool的值
 */

class PersonalProfileSchoolModel implements IPersonalProfileSchool.Model{

    private String nickName;
    private String newSchool;

    @Override
    public void setNickName(String nickName){
        this.nickName=nickName;
    }
    @Override
    public void setNewSchool(String newSchool){
        this.newSchool=newSchool;
    }
    @Override
    public void revampSchool(ValueCallBack<String> callBack){
        if(JudgeObjectIsEmpty.isNotEmpty(nickName)||JudgeObjectIsEmpty.isNotEmpty(newSchool)
                ||!nickName.isEmpty()){
            callBack.onSuccess("修改成功");
        }
        else{
            callBack.onFail("修改失败，请重试");
        }
    }
}

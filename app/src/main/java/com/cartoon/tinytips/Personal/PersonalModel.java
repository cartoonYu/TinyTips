package com.cartoon.tinytips.Personal;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.data.TablePersonalInformation.PersonalInformation;
import com.cartoon.tinytips.util.JudgeObjectIsEmpty;

/**
 * Created by cartoon on 2018/3/6.
 *在getNoteList函数中赋值给getPersonalInformation完成功能
 */

class PersonalModel implements IPersonal.Model{
    @Override
    public void getPersonalInformation(ValueCallBack<PersonalInformation> callBack){
        PersonalInformation information=new PersonalInformation();
        if(JudgeObjectIsEmpty.isNotEmpty(information)){
            callBack.onSuccess(information);
        }
        else{
            callBack.onFail("获取个人信息失败，请重试");
        }
    }
}

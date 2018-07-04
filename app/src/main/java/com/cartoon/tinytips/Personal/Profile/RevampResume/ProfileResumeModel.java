package com.cartoon.tinytips.Personal.Profile.RevampResume;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.util.JudgeObjectIsEmpty;

/**
 * Created by cartoon on 2018/3/7.
 */

class ProfileResumeModel implements IProfileResume.Model{

    private String nickName;
    private String newResume;

    @Override
    public void setNickName(String nickName){
        this.nickName=nickName;
    }
    @Override
    public void setNewResume(String newResume){
        this.newResume=newResume;
    }
    @Override
    public void revampResume(ValueCallBack<String> callBack){
        if(JudgeObjectIsEmpty.isNotEmpty(nickName)||JudgeObjectIsEmpty.isNotEmpty(newResume)
                ||!nickName.isEmpty()){
            callBack.onSuccess("修改成功");
        }
        else{
            callBack.onFail("修改失败，请重试");
        }
    }
}

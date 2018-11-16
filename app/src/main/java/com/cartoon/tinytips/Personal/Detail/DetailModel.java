package com.cartoon.tinytips.Personal.Detail;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Local.LocalInformation;
import com.cartoon.tinytips.util.JudgeEmpty;

public class DetailModel implements IDetail.Model {

    private Information info;

    @Override
    public void getInformation(ValueCallBack<Information> callBack){
        info=LocalInformation.getLocalInformation().query();
        if (JudgeEmpty.isEmpty(info)){
            callBack.onFail("获取个人信息失败");
        }else {
            callBack.onSuccess(info);
        }
    }

}
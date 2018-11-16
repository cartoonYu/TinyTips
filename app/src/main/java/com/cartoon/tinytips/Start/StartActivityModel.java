package com.cartoon.tinytips.Start;

import android.util.Log;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Local.LocalInformation;
import com.cartoon.tinytips.bean.Operate.OperateInformation;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.ShowToast;

import java.util.List;

public class StartActivityModel implements IStartActivity.Model {

    private LocalInformation localInformation;

    private List<Information> list;

    private Information info;

    @Override
    public void getPersonalInformation(ValueCallBack<Information> callBack) {
        info=localInformation.query();
        if(JudgeEmpty.isEmpty(info)){
            callBack.onFail("尚未登录");
            return;
        }
        callBack.onSuccess(info);
    }


    public StartActivityModel(){
        localInformation=LocalInformation.getLocalInformation();
    }
}

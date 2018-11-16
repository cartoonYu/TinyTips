package com.cartoon.tinytips.Login;


import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Local.LocalInformation;
import com.cartoon.tinytips.bean.Operate.OperateInformation;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.ShowToast;

import java.util.List;

public class LoginModel implements ILogin.Model {

    private OperateInformation operateInformation;

    private LocalInformation localInformation;

    @Override
    public void checkInformation(Information information,ValueCallBack<String> callBack) {
        if(JudgeEmpty.isEmpty(information)){
            callBack.onFail("系统错误，请重试");
            return;
        }
        operateInformation.query(information);
        while(operateInformation.isNotFinish()){
            ShowToast.shortToast("正在登录，请稍后");
        }
        List<Information> result=operateInformation.getQueryData();
        if(result.isEmpty()){
            callBack.onFail("输入错误，请重试");
        }
        else {
            localInformation.add(result.get(0));
            callBack.onSuccess("登录成功");
        }
    }

    public LoginModel(){
        operateInformation=OperateInformation.getOperateInformation();
        localInformation=LocalInformation.getLocalInformation();
    }
}

package com.cartoon.tinytips.Personal.Setting.Security;

import android.util.Log;

import com.cartoon.tinytips.Login.Login;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Local.LocalInformation;
import com.cartoon.tinytips.bean.Operate.OperateInformation;

public class SecurityModel implements ISecurity.Model {

    private String authCode;   //验证码

    private Information info;

    private LocalInformation localInformation;

    private OperateInformation operateInformation;

    @Override
    public void changePassword(String phone, String authCode, String password, String confirmPassword, ValueCallBack<String> callBack) {
        if(!info.getAccount().equals(phone)){
            callBack.onFail("手机号码输入错误，请重试");
            return;
        }
        if(!password.equals(confirmPassword)){
            callBack.onFail("两次输入密码不一致，请重试");
            return;
        }
        Information information=new Information();
        information.setPassword(password);
        operateInformation.update(info,information);
        while (operateInformation.isNotFinish()){
        }
        if(operateInformation.isSuccess()){
            info.setPassword(password);
            if(localInformation.update(info)){
                callBack.onSuccess("修改成功");
            }
            else {
                callBack.onFail("修改失败，请重试");
            }
        }
    }

    @Override
    public void verifyAuthCode(ValueCallBack<Boolean> callBack) {

    }

    public SecurityModel(){
        localInformation=LocalInformation.getLocalInformation();
        info=localInformation.query();
        operateInformation=OperateInformation.getOperateInformation();
    }

}

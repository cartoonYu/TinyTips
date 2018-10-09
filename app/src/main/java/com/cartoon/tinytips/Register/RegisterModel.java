package com.cartoon.tinytips.Register;

import android.util.Log;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Operate.OperateInformation;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.ShowToast;
import com.cartoon.tinytips.util.network.HttpConstant;

import java.util.ArrayList;
import java.util.List;

class RegisterModel implements IRegister.Model {

    private String authCode;   //验证码

    private Information information;

    private String result;


    @Override
    public void setAuthCode(String authCode){
        this.authCode=authCode;
    }

    @Override
    public void verifyAuthCode(ValueCallBack<Boolean> callBack){
        callBack.onSuccess(true);
    }

    @Override
    public void setInformation(Information information){
        this.information=information;
    }

    @Override
    public void verifyInformation(ValueCallBack<String> callBack){
        //callBack.onSuccess(new String("注册成功"));
        Information information=new Information();
        information.setNickName("cartoon");
        OperateInformation op=OperateInformation.getOperate();
        List<Information> list=op.query(information);
    }
}

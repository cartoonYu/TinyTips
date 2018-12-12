package com.cartoon.tinytips.Register;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Local.LocalInformation;
import com.cartoon.tinytips.bean.Operate.OperateInformation;
import com.cartoon.tinytips.util.JudgeEmpty;

class RegisterModel implements IRegister.Model {

    private OperateInformation operateInformation;

    private LocalInformation localInformation;

    @Override
    public void register(Information information, ValueCallBack<String> callBack) {
        if(information.getAccount().equals("")){
            callBack.onFail("尚未填写账号");
            return;
        }
        if(information.getNickName().equals("")){
            callBack.onFail("尚未填写昵称");
            return;
        }
        if(information.getPassword().equals("")){
            callBack.onFail("尚未填写密码");
            return;
        }
        operateInformation.add(information);
        while (operateInformation.isNotFinish()){
        }
        if(operateInformation.isSuccess()){
            operateInformation.query(information);
            while (operateInformation.isNotFinish()){
            }
            information=operateInformation.getQueryData().get(0);
            if(localInformation.add(information)){
                callBack.onSuccess("注册成功");
                return;
            }
        }
        callBack.onFail("注册失败，请重试");
    }

    public RegisterModel(){
        operateInformation=OperateInformation.getOperateInformation();
        localInformation=LocalInformation.getLocalInformation();
    }
}

package com.cartoon.tinytips.Login;


import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.bean.table.Local.LocalInformation;
import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.table.Operate.OperateInformation;
import com.cartoon.tinytips.util.JudgeEmpty;

import java.util.List;

public class LoginModel implements ILogin.Model {

    private OperateInformation operateInformation;

    private LocalInformation localInformation;

    @Override
    public void checkInformation(Information information,final ValueCallBack<String> callBack) {
        if(JudgeEmpty.isEmpty(information)){
            callBack.onFail("系统错误，请重试");
            return;
        }
        operateInformation.query(information, new IOperateBean<List<Information>>() {
            @Override
            public void onSuccess(List<Information> information) {
                localInformation.add(information.get(0));
                callBack.onSuccess("登录成功");
            }

            @Override
            public void onFail(String msg) {
                switch (msg){
                    case "300":{
                        callBack.onFail("系统错误，请重试");
                        break;
                    }
                    case "400":{
                        callBack.onFail("输入错误，请重试");
                        break;
                    }
                    case "500":{
                        callBack.onFail("输入错误，请重试");
                        break;
                    }
                }
            }
        });
    }

    public LoginModel(){
        operateInformation=OperateInformation.getOperateInformation();
        localInformation=LocalInformation.getLocalInformation();
    }
}

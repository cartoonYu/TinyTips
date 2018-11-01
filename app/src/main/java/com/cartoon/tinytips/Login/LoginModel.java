package com.cartoon.tinytips.Login;

import android.util.Log;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Operate.OperateInformation;

import java.util.List;

public class LoginModel implements ILogin.Model {
    private List<Information> list;

    private Information info;

    private OperateInformation operater;
    @Override
    public void getPersonalInformation(ValueCallBack<Information> callBack) {
 /*       list = operater.query(new Information("13458985630","123"));
        info = list.get(0);
        Log.d("onPrepare", "getPersonalInformation: ");
        Log.d("onPrepare: ",info.getAccount()+info.getPassword());
        if (info.getAccount().equals("13458985630")&&info.getPassword().equals("123")){
            callBack.onSuccess(info);
        }
        else {
            callBack.onFail("获取个人信息失败");
        }*/
    }
}

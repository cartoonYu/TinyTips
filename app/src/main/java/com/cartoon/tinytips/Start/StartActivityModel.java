package com.cartoon.tinytips.Start;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Operate.OperateInformation;
import com.cartoon.tinytips.util.ShowToast;

import java.util.List;

public class StartActivityModel implements IStartActivity.Model {
    private List<Information> list;

    private Information info;

    private Information queryCondition;

    @Override
    public void getPersonalInformation(ValueCallBack<Information> callBack) {
        queryCondition = new Information();
        queryCondition.setAccount("13458985630");
        queryCondition.setPassword("123");
        OperateInformation operateInformation =OperateInformation.getOperateInformation();
        operateInformation.query(queryCondition);
        while (operateInformation.isNotFinish()){
            ShowToast.shortToast("正在登录");
        }
        list= operateInformation.getQueryData();
        info = list.get(0);
        if (info.getAccount().equals("13458985630")&&info.getPassword().equals("123")){
            callBack.onSuccess(info);
        }
        else {
            callBack.onFail("获取个人信息失败");
        }
    }
}

package com.cartoon.tinytips.Main;

import android.util.Log;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Operate.OperateInformation;

import java.util.List;

public class MainModel implements IMain.Model {
    private List<Information> list;

    private Information info;

    private OperateInformation operater;

    private Information queryCondition;

    @Override
    public void getPersonalInformation(ValueCallBack<Information> callBack) {
      /*  queryCondition = new Information();
        queryCondition.setAccount("13458985630");
        queryCondition.setPassword("123");
        operater = OperateInformation.getOperate();
        list = operater.query(queryCondition);
        info = list.get(0);
        Log.d("onPrepare: ", list.size() + "");
        Log.d("onPrepare: ",info.getAccount()+info.getPassword());*/
        /*info = list.get(0);
        Log.d("onPrepare", "getPersonalInformation: ");

        if (info.getAccount().equals("13458985630")&&info.getPassword().equals("123")){
            callBack.onSuccess(info);
            Log.d("onPrepare", "model ");
            Log.d("onPrepare: ",info.getAccount()+info.getPassword());
        }
        else {
            callBack.onFail("获取个人信息失败");
        }
    }*/

    }
}

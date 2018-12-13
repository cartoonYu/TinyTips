package com.cartoon.tinytips.Personal.Detail.Revamp;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Local.LocalInformation;
import com.cartoon.tinytips.bean.Operate.OperateInformation;

public class RevampInformationModel implements IRevampInformation.Model {

    private OperateInformation operateInformation;

    private LocalInformation localInformation;

    @Override
    public void revampInformation(Information information, ValueCallBack<String> callBack) {
        Information oldInformation=localInformation.query();
        information.setSex(oldInformation.isSex());
        operateInformation.update(oldInformation,information);
        while (operateInformation.isNotFinish()){
        }
        if(operateInformation.isSuccess()){
            if(localInformation.update(information)){
                callBack.onSuccess("修改成功");
                return;
            }
        }
        callBack.onFail("修改失败，请重试");
    }

    public RevampInformationModel(){
        operateInformation=OperateInformation.getOperateInformation();
        localInformation=LocalInformation.getLocalInformation();
    }
}

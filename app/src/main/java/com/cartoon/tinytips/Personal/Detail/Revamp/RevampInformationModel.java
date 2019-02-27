package com.cartoon.tinytips.Personal.Detail.Revamp;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.bean.table.Local.ILocalInformation;
import com.cartoon.tinytips.bean.table.Local.LocalInformation;
import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.table.Operate.OperateInformation;
import com.cartoon.tinytips.bean.table.Operate.imp.IOperateInformation;

public class RevampInformationModel implements IRevampInformation.Model {

    private IOperateInformation operateInformation;

    private ILocalInformation localInformation;

    @Override
    public void revampInformation(final Information information,final ValueCallBack<String> callBack) {
        Information oldInformation=localInformation.query();
        information.setSex(oldInformation.isSex());
        operateInformation.update(oldInformation, information, new IOperateBean<String>() {
            @Override
            public void onSuccess(String s) {
                if(localInformation.update(information)){
                    callBack.onSuccess("修改成功");
                }
                else {
                    callBack.onFail("修改失败，请重试");
                }
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail("修改失败，请重试");
            }
        });

    }

    public RevampInformationModel(){
        operateInformation=OperateInformation.getOperateInformation();
        localInformation=LocalInformation.getLocalInformation();
    }
}

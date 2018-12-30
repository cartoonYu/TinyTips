package com.cartoon.tinytips.Register;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.bean.table.Local.LocalInformation;
import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.table.Operate.OperateInformation;

import java.util.List;

class RegisterModel implements IRegister.Model {

    private OperateInformation operateInformation;

    private LocalInformation localInformation;

    @Override
    public void register(final Information information,final ValueCallBack<String> callBack) {
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
        operateInformation.add(information, new IOperateBean<String>() {
            @Override
            public void onSuccess(String s) {
                operateInformation.query(information, new IOperateBean<List<Information>>() {
                    @Override
                    public void onSuccess(List<Information> information) {
                        if(localInformation.add(information.get(0))){
                            callBack.onSuccess("注册成功");
                        }
                        else {
                            callBack.onFail("注册失败，请重试");
                        }
                    }

                    @Override
                    public void onFail(String msg) {
                        callBack.onFail("注册失败，请重试");
                    }
                });
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    public RegisterModel(){
        operateInformation=OperateInformation.getOperateInformation();
        localInformation=LocalInformation.getLocalInformation();
    }
}

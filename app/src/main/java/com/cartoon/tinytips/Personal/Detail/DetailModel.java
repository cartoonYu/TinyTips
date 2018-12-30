package com.cartoon.tinytips.Personal.Detail;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.bean.table.Local.LocalInformation;
import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.table.Operate.OperateInformation;
import com.cartoon.tinytips.util.JudgeEmpty;

import java.util.List;

public class DetailModel implements IDetail.Model {

    private LocalInformation localInformation;

    private Information info;

    private OperateInformation operateInformation;

    @Override
    public void getInformation(ValueCallBack<Information> callBack){
        info=localInformation.query();
        if (JudgeEmpty.isEmpty(info)){
            callBack.onFail("获取个人信息失败");
        }else {
            callBack.onSuccess(info);
        }
    }

    @Override
    public void revampInformation(Information information,final ValueCallBack<String> callBack) {
        operateInformation.update(info, information, new IOperateBean<String>() {
            @Override
            public void onSuccess(String s) {
                Information information=new Information();
                information.setId(info.getId());
                operateInformation.query(information, new IOperateBean<List<Information>>() {
                    @Override
                    public void onSuccess(List<Information> information) {
                        if(localInformation.update(information.get(0))){
                            callBack.onSuccess("上传成功");
                        }
                        else {
                            callBack.onFail("上传失败，请重试");
                        }
                    }

                    @Override
                    public void onFail(String msg) {
                        callBack.onFail("上传失败，请重试");
                    }
                });
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail("上传失败，请重试");
            }
        });
    }

    public DetailModel(){
        localInformation=LocalInformation.getLocalInformation();
        operateInformation=OperateInformation.getOperateInformation();
    }
}
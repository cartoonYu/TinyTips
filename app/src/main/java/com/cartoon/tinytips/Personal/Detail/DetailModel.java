package com.cartoon.tinytips.Personal.Detail;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Local.LocalInformation;
import com.cartoon.tinytips.bean.Operate.OperateInformation;
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
    public void revampInformation(Information information, ValueCallBack<String> callBack) {
        operateInformation.update(info,information);
        while (operateInformation.isNotFinish()){
        }
        if(operateInformation.isSuccess()){
            if(changeInformation()){
                callBack.onSuccess("上传成功");
            }
        }
        else {
            callBack.onFail("上传失败，请重试");
        }
    }

    /**
     * 功能
     * 从服务器查询更改后的个人信息，并修改本地数据文件
     * @return
     */
    private boolean changeInformation(){
        Information information=new Information();
        information.setId(info.getId());
        operateInformation.query(information);
        while (operateInformation.isNotFinish()){
        }
        List<Information> list=operateInformation.getQueryData();
        if(!list.isEmpty()){
            info=list.get(0);
            if(localInformation.update(info)){
                return true;
            }
        }
        return false;
    }

    public DetailModel(){
        localInformation=LocalInformation.getLocalInformation();
        operateInformation=OperateInformation.getOperateInformation();
    }
}
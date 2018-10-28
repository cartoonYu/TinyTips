package com.cartoon.tinytips.Personal.Detail;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Operate.OperateInformation;

import java.util.List;

public class DetailModel implements IDetail.Model {

    @Override
    public void getPersonalInformation(ValueCallBack<Information> callBack){
        /*Information information=new Information();
        Bitmap headPro=BitmapFactory.decodeResource(TinyTipsApplication.getContext().getResources(), R.drawable.nav_icon);
        if(JudgeEmpty.isEmpty(headPro)){

        }
        else{
            //information.setHeadPortrait(headPro);
            information.setNickName("AlphaCat");
            information.setMajor("软件工程");
            information.setSchool("五邑大学");
            information.setResume("我是简介哦");
            information.setDegree("本科");
            information.setDate("2018-9-28");
            callBack.onSuccess(information);
        }*/
        Information information=new Information();
        information.setAccount("hht");
        OperateInformation op=OperateInformation.getOperate();
        List<Information> list=op.query(information);
        if(list.isEmpty()){
            callBack.onFail("获取个人信息错误");
        }
        else {
            Information result=list.get(0);
            callBack.onSuccess(result);
        }

    }
}
package com.cartoon.tinytips.Personal.Detail;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.cartoon.tinytips.R;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.TinyTipsApplication;

public class DetailModel implements IDetail.Model {

    @Override
    public void getPersonalInformation(ValueCallBack<Information> callBack){
        Information information=new Information();
        Bitmap headPro=BitmapFactory.decodeResource(TinyTipsApplication.getContext().getResources(), R.drawable.nav_icon);
        if(JudgeEmpty.isEmpty(headPro)){

        }
        else{
            information.setHeadPortrait(headPro);
            information.setNickName("AlphaCat");
            information.setMajor("软件工程");
            information.setSchool("五邑大学");
            information.setResume("我是简介哦");
            information.setDegree("本科");
            callBack.onSuccess(information);
        }
    }
}

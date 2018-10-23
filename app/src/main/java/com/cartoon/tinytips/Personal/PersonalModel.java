package com.cartoon.tinytips.Personal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.cartoon.tinytips.R;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Operate.OperateInformation;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.TinyTipsApplication;

import java.util.List;

class PersonalModel implements IPersonal.Model {
    private String NumOfNote;

    private String NumOfAttentions;

    private String NumOfFans;

    @Override
    public void getPersonalInformation(ValueCallBack<Information> callBack){
        /*Information information=new Information();
        Bitmap headPro=BitmapFactory.decodeResource(TinyTipsApplication.getContext().getResources(), R.drawable.nav_icon);
        if(JudgeEmpty.isEmpty(headPro)){
        }
        else{
            NumOfAttentions = "2";
            NumOfFans = "2";
            NumOfNote = "2";

            //information.setHeadPortrait(headPro);
            information.setNumOfNote("笔记  "+NumOfNote);
            information.setNumOfFans("粉丝  "+NumOfFans);
            information.setNumOfAttentions("关注  "+NumOfAttentions);
            information.setNickName("AlphaCat");
            callBack.onSuccess(information);
        }*/

    }
}

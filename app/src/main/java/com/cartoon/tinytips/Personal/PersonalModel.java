package com.cartoon.tinytips.Personal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.cartoon.tinytips.R;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.TinyTipsApplication;

class PersonalModel implements IPersonal.Model {

    @Override
    public void getPersonalInformation(ValueCallBack<Information> callBack){
        Information information=new Information();
        Bitmap headPro=BitmapFactory.decodeResource(TinyTipsApplication.getContext().getResources(), R.mipmap.back_black);
        if(JudgeEmpty.isEmpty(headPro)){

        }
        else{
            information.setHeadPortrait(headPro);
            callBack.onSuccess(information);
        }
    }
}

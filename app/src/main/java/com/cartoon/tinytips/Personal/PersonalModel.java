package com.cartoon.tinytips.Personal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;

import com.cartoon.tinytips.R;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.PersonalInformation;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.TinyTipsApplication;

class PersonalModel implements IPersonal.Model {

    @Override
    public void getPersonalInformation(ValueCallBack<PersonalInformation> callBack){
        PersonalInformation information=new PersonalInformation();
        Bitmap headPro=BitmapFactory.decodeResource(TinyTipsApplication.getContext().getResources(), R.mipmap.back_black);
        if(JudgeEmpty.isEmpty(headPro)){

        }
        else{
            information.setHeadPortrait(headPro);
            callBack.onSuccess(information);
        }
    }
}

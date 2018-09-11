package com.cartoon.tinytips.Register;

import android.util.Log;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.PersonalInformation;
import com.cartoon.tinytips.util.network.HttpConnection;

import org.json.JSONException;
import org.json.JSONObject;

class RegisterModel implements IRegister.Model {

    private String authCode;   //验证码

    private PersonalInformation information;


    @Override
    public void setAuthCode(String authCode){
        this.authCode=authCode;
    }

    @Override
    public void verifyAuthCode(ValueCallBack<Boolean> callBack){
        callBack.onSuccess(true);
    }

    @Override
    public void setInformation(PersonalInformation information){
        this.information=information;
    }

    @Override
    public void verifyInformation(ValueCallBack<String> callBack){
        //callBack.onSuccess(new String("注册成功"));
        JSONObject object=new JSONObject();
        try {
            object.put("asds","sqs");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url=new String("http://192.168.31.29:8080/TinyTipsWEB/Test");
        HttpConnection connection= HttpConnection.getConnection();
        connection.sendData(url,"POST",object);

        if(connection.getResult()){
            callBack.onSuccess("edqedwfwef");
        }
    }
}

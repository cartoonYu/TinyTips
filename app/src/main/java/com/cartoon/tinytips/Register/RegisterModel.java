package com.cartoon.tinytips.Register;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.PersonalInformation;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.ShowToast;
import com.cartoon.tinytips.util.network.HttpConnection;
import com.cartoon.tinytips.util.network.HttpConstant;
import com.cartoon.tinytips.util.network.JSONOperation;

import org.json.JSONException;
import org.json.JSONObject;

class RegisterModel implements IRegister.Model {

    private String authCode;   //验证码

    private PersonalInformation information;

    private String result;


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
        String url= HttpConstant.getConstant().getURL_Test();
        HttpConnection httpConnection=HttpConnection.getHttpConnection();
        httpConnection.sendData(url,"POST",object);
        Thread thread=new Thread(httpConnection);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JSONObject result=httpConnection.getResult();
        if(JudgeEmpty.isNotEmpty(result)){
            ShowToast.shortToast(JSONOperation.getInstance().getResultFromJSON(object));
        }
    }
}

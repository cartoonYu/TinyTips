package com.cartoon.tinytips.Register;

import android.graphics.Path;
import android.util.Log;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Operate.OperateInformation;
import com.cartoon.tinytips.util.Image.ImageOperation;
import com.cartoon.tinytips.util.JSON.JSONObjectOperation;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.ShowToast;
import com.cartoon.tinytips.util.network.HttpConnection;
import com.cartoon.tinytips.util.network.HttpConstant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class RegisterModel implements IRegister.Model {

    private String authCode;   //验证码

    private Information information;

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
    public void setInformation(Information information){
        this.information=information;
    }

    @Override
    public void verifyInformation(ValueCallBack<String> callBack){
        //callBack.onSuccess(new String("注册成功"));
        Information information=new Information();
        information.setAccount("cartoon");
        JSONObjectOperation objectOperation=JSONObjectOperation.getInstance();
        JSONObject object=objectOperation.setInformationToJSON(information,"update");
        Log.d("asd",object.toString());
        HttpConnection connection=HttpConnection.getHttpConnection();
        connection.sendJSONObject(HttpConstant.getConstant().getURL_Text(),"POST",object);
        Thread thread=new Thread(connection);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Information n=null;
        try {
            JSONObject jsonObject=new JSONObject(connection.getResult());
            n=objectOperation.getInformationFromJSON(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ImageOperation operation=ImageOperation.getOperation();
        operation.transStringToFile(n.getHeadPortraitResource(),n.getHeadPortraitName());
    }
}

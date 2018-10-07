package com.cartoon.tinytips.bean.Operate;

import android.util.Log;

import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.util.JSON.JSONArrayOperation;
import com.cartoon.tinytips.util.JSON.JSONObjectOperation;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.ShowToast;
import com.cartoon.tinytips.util.network.HttpConnection;
import com.cartoon.tinytips.util.network.HttpConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OperateInformation {

    private static volatile OperateInformation operate;

    private JSONObjectOperation objectOperation;

    private JSONArrayOperation arrayOperation;

    private HttpConnection connection;


    private String url;

    private String method;

    public static OperateInformation getOperate(){
        if(JudgeEmpty.isEmpty(operate)){
            synchronized (OperateInformation.class){
                if(JudgeEmpty.isEmpty(operate)){
                    operate=new OperateInformation();
                }
            }
        }
        return operate;
    }

    public boolean add(Information information){
        JSONObject data=objectOperation.setInformationToJSON(information,"add");
        connection.sendJSONObject(url,method,data);
        Thread thread=new Thread(connection);
        thread.start();
        JSONObject object=null;
        try {
            thread.join();
            object=new JSONObject(connection.getResult());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }
        if(JudgeEmpty.isNotEmpty(object)){
            if(objectOperation.getResultFromJSON(object).equals("200")){
                return true;
            }
        }
        return false;
    }

    public boolean delete(Information condition){
        JSONObject data=objectOperation.setInformationToJSON(condition,"delete");
        connection.sendJSONObject(url,method,data);
        Thread thread=new Thread(connection);
        thread.start();
        JSONObject object=null;
        try {
            thread.join();
            object=new JSONObject(connection.getResult());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }
        if(JudgeEmpty.isNotEmpty(object)){
            if(objectOperation.getResultFromJSON(object).equals("200")){
                return true;
            }
        }
        return false;
    }

    public List<Information> query(Information condition){
        JSONObject data=objectOperation.setInformationToJSON(condition,"query");
        connection.sendJSONObject(url,method,data);
        Thread thread=new Thread(connection);
        thread.start();
        JSONArray array=null;

        try {
            thread.join();
            array=new JSONArray(connection.getResult());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }

        List<Information> result=new ArrayList<>();
        List<JSONObject> temp=null;
        if(JudgeEmpty.isNotEmpty(array)){
            temp=arrayOperation.getObjectsFromArray(array);
        }
        if(JudgeEmpty.isNotEmpty(temp)){
            for(JSONObject object:temp){
                result.add(objectOperation.getInformationFromJSON(object));
            }
        }
        return result;
    }

    public boolean update(Information oldInformation,Information newInformation){
        JSONObject condition=objectOperation.setInformationToJSON(oldInformation,"update");
        JSONObject data=objectOperation.setInformationToJSON(newInformation,"update");
        List<JSONObject> temp=new ArrayList<>();
        temp.add(condition);
        temp.add(data);
        JSONArray array=arrayOperation.setObjectToArray(temp);
        connection.sendJSONArray(url,method,array);
        Thread thread=new Thread(connection);
        thread.start();
        JSONObject object=null;
        try {
            thread.join();
            object=new JSONObject(connection.getResult());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }
        if(JudgeEmpty.isNotEmpty(object)){
            if(objectOperation.getResultFromJSON(object).equals("200")){
                return true;
            }
        }
        return false;
    }

    private OperateInformation(){
        objectOperation=JSONObjectOperation.getInstance();
        arrayOperation=JSONArrayOperation.getOperation();
        connection=HttpConnection.getHttpConnection();
        url=HttpConstant.getConstant().getURL_Information();
        method="POST";
    }


}

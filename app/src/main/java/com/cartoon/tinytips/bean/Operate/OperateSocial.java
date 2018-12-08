package com.cartoon.tinytips.bean.Operate;

import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Social;
import com.cartoon.tinytips.util.JSON.JSONArrayOperation;
import com.cartoon.tinytips.util.JSON.JSONObjectOperation;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.network.HttpConnection;
import com.cartoon.tinytips.util.network.HttpConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OperateSocial {

    private static volatile OperateSocial operateSocial;

    private JSONObjectOperation objectOperation;

    private JSONArrayOperation arrayOperation;

    private HttpConnection connection;

    private String url;

    private String method;

    private boolean isNotFinish;

    private boolean isSuccess;

    private List<Social> queryData;


    /**
     * 功能
     * 插入社区信息
     *
     * 使用方法
     * 1.传入社区对象
     * 2.通过方法isSuccess获取插入结果
     *
     * 注意
     * 1.传入社区对象必须携带笔记ID以及用户ID
     *
     * @param social
     * @return
     */
    public void add(Social social){
        JSONObject data=objectOperation.setSocialToJSON(social,"add");
        String result=sendData(data);
        setSuccess(result);
    }

    /**
     * 功能
     * 删除个人信息
     *
     * 使用方法
     * 1.传入社区对象
     * 2.通过方法isSuccess判断删除是否成功
     *
     * 注意
     * 1.传入社区对象必须携带至少一个条件
     *
     * @param condition
     * @return
     */
    public void delete(Social condition){
        JSONObject data=objectOperation.setSocialToJSON(condition,"delete");
        String result=sendData(data);
        setSuccess(result);
    }

    /**
     * 功能
     * 查询社区信息
     *
     * 使用方法
     * 1.传入社区对象
     *
     * 注意
     * 1.传入社区对象必须携带至少一个条件
     * 2.通过方法getQueryData获取返回的个人信息集合
     *
     * @param condition
     * @return
     */
    public void query(Social condition){
        JSONObject data=objectOperation.setSocialToJSON(condition,"query");
        String result=sendData(data);
        setQueryData(result);
    }

    /**
     * 功能
     * 调用网络连接工具类发送数据
     *
     * 使用方法
     *
     * @param object
     * @return
     */
    private String sendData(JSONObject object){
        if(JudgeEmpty.isNotEmpty(object)){
            connection.sendJSONObject(url,method,object);
        }
        new Thread(connection).start();
        while (connection.isRun()){
        }
        setNotFinish(false);
        return connection.getResult();
    }

    /**
     * 功能
     * 判断网络传输是否完成
     *
     * @return
     */
    public boolean isNotFinish() {
        return isNotFinish;
    }

    /**
     * 获取插入，删除，更新数据是否成功
     * @return
     */
    public boolean isSuccess() {
        setNotFinish(true);
        return isSuccess;
    }

    private void setNotFinish(boolean notFinish) {
        isNotFinish = notFinish;
    }

    private void setSuccess(String result) {
        JSONObject object=null;
        try {
            object=new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(JudgeEmpty.isNotEmpty(object)){
            if(objectOperation.getResultFromJSON(object).equals("200")){
                isSuccess=true;
            }
            else {
                isSuccess=false;
            }
        }
    }

    /**
     * 功能
     * 将服务器返回的查询数据转换成集合形式输出
     *
     * @param result
     */
    private void setQueryData(String result) {
        queryData.clear();
        if(result.equals("[]")){
            return;
        }
        JSONArray array=null;
        try {
            array=new JSONArray(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<JSONObject> temp=null;
        if(JudgeEmpty.isNotEmpty(array)){
            temp=arrayOperation.getObjectsFromArray(array);
        }
        if(JudgeEmpty.isNotEmpty(temp)){
            for(JSONObject object:temp){
                queryData.add(objectOperation.getSocialFromJSON(object));
            }
        }
    }

    /**
     * 获取查询的结果
     *
     * @return
     */
    public List<Social> getQueryData() {
        setNotFinish(true);
        return queryData;
    }

    private OperateSocial(){
        objectOperation=JSONObjectOperation.getInstance();
        arrayOperation=JSONArrayOperation.getOperation();
        connection=HttpConnection.getConnection();
        url=HttpConstant.getConstant().getURL_Social();
        method="POST";
        queryData=new ArrayList<>();
        setNotFinish(true);
    }

    public static OperateSocial getOperateSocial(){
        if(JudgeEmpty.isEmpty(operateSocial)){
            synchronized (OperateSocial.class){
                if(JudgeEmpty.isEmpty(operateSocial)){
                    operateSocial=new OperateSocial();
                }
            }
        }
        return operateSocial;
    }
}

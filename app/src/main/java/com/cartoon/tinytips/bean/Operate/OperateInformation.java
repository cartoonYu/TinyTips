package com.cartoon.tinytips.bean.Operate;

import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.util.JSON.JSONArrayOperation;
import com.cartoon.tinytips.util.JSON.JSONObjectOperation;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.network.HttpConstant;
import com.cartoon.tinytips.util.network.HttpConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cartoon
 * @version 1.3
 *
 * description
 * 通过网络传输对数据库个人信息表进行增删查改操作
 *
 * how to use
 * 1.通过静态方法getOperateInformation获取本类对象
 * 2.根据相应操作执行相应的方法
 *   add(插入个人信息)
 *   delete(删除个人信息)
 *   query(查询个人信息)
 *   update(更新个人信息)
 * 3.因网络传输以及数据库操作需要时间，
 *   通过方法isNotFinish()判断操作是否完成（true为未完成）
 * 4.获取结果
 *   插入，删除，查询，更新操作通过方法isSuccess()获取操作结果
 *   查询操作通过方法getQueryData()获取结果集合(若list为空，则查询失败)
 *
 * notice
 * 1.本类无法new对象，只能通过静态方法getOperateInformation获取
 */

public class OperateInformation {

    private static volatile OperateInformation operateInformation;

    private JSONObjectOperation objectOperation;

    private JSONArrayOperation arrayOperation;

    private HttpConnection connection;

    private String url;

    private String method;

    private boolean isNotFinish;

    private boolean isSuccess;

    private List<Information> queryData;

    /**
     * 功能
     * 返回本类对象，确保在程序运行的过程只有一个对象
     *
     * 使用方法
     * 1.类名直接调用获取
     *
     * @return
     */
    public static OperateInformation getOperateInformation(){
        if(JudgeEmpty.isEmpty(operateInformation)){
            synchronized (OperateInformation.class){
                if(JudgeEmpty.isEmpty(operateInformation)){
                    operateInformation =new OperateInformation();
                }
            }
        }
        return operateInformation;
    }

    /**
     * 功能
     * 插入个人信息
     *
     * 使用方法
     * 1.传入个人信息对象
     * 2.通过方法isSuccess获取插入结果
     *
     * 注意
     * 1.传入个人信息对象必须携带账号
     *
     * @param information
     * @return
     */
    public void add(Information information){
        JSONObject data=objectOperation.setInformationToJSON(information,"add");
        String result=sendData(data,null);
        setSuccess(result);
    }

    /**
     * 功能
     * 删除个人信息
     *
     * 使用方法
     * 1.传入个人信息对象
     * 2.通过方法isSuccess判断删除是否成功
     *
     * 注意
     * 1.传入个人信息对象必须携带至少一个条件
     *
     * @param condition
     * @return
     */
    public void delete(Information condition){
        JSONObject data=objectOperation.setInformationToJSON(condition,"delete");
        String result=sendData(data,null);
        setSuccess(result);
    }

    /**
     * 功能
     * 查询个人信息
     *
     * 使用方法
     * 1.传入个人信息对象
     *
     * 注意
     * 1.传入个人信息对象必须携带至少一个条件
     * 2.通过方法getQueryData获取返回的个人信息集合
     *
     * @param condition
     * @return
     */
    public void query(Information condition){
        JSONObject data=objectOperation.setInformationToJSON(condition,"query");
        String result=sendData(data,null);
        setQueryData(result);
    }

    /**
     * 功能
     * 更新个人信息
     *
     * 使用方法
     * 1.传入原有个人信息以及修改后的个人信息对象
     * 2.通过返回值判断更新是否成功
     *
     * 注意
     * 1.传入的原有个人信息对象必须携带至少一个条件
     *
     * @param oldInformation
     * @param newInformation
     * @return
     */
    public void update(Information oldInformation,Information newInformation){
        JSONObject condition=objectOperation.setInformationToJSON(oldInformation,"update");
        JSONObject data=objectOperation.setInformationToJSON(newInformation,"update");
        List<JSONObject> temp=new ArrayList<>();
        temp.add(condition);
        temp.add(data);
        JSONArray array=arrayOperation.setObjectToArray(temp);
        String result=sendData(null,array);
        setSuccess(result);
    }

    /**
     * 功能
     * 调用网络连接工具类发送数据
     *
     * 使用方法
     *
     * 注意
     * 1.两个形参中必须有一个为null
     *
     * @param object
     * @param array
     * @return
     */
    private String sendData(JSONObject object,JSONArray array){
        if(JudgeEmpty.isNotEmpty(object)){
            connection.sendJSONObject(url,method,object);
        }else if(JudgeEmpty.isNotEmpty(array)){
            connection.sendJSONArray(url,method,array);
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

    /**
     * 获取查询的结果
     *
     * @return
     */
    public List<Information> getQueryData() {
        setNotFinish(true);
        return queryData;
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
                queryData.add(objectOperation.getInformationFromJSON(object));
            }
        }
    }

    private OperateInformation(){
        objectOperation=JSONObjectOperation.getInstance();
        arrayOperation=JSONArrayOperation.getOperation();
        connection=HttpConnection.getConnection();
        url=HttpConstant.getConstant().getURL_Information();
        method="POST";
        setNotFinish(true);
        queryData=new ArrayList<>();
    }
}

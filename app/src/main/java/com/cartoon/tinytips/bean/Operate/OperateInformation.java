package com.cartoon.tinytips.bean.Operate;

import com.cartoon.tinytips.bean.Information;
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

/**
 * @author cartoon
 * @version 1.0
 *
 * description
 * 1.个人信息操作类
 * 2.本类有四个公有成员方法
 *      1）插入个人信息
 *      2）删除个人信息
 *      3）查询个人信息
 *      4）更新个人信息
 *
 * notice
 * 1.本类为单例
 * 2.对象通过调用静态方法getConstant获取
 * 3.成员方法的使用请看方法的doc
 */

public class OperateInformation {

    private static volatile OperateInformation operate;

    private JSONObjectOperation objectOperation;

    private JSONArrayOperation arrayOperation;

    private HttpConnection connection;

    private String url;

    private String method;

    /**
     * 功能
     * 获取本类对象
     *
     * 使用方法
     * 1.直接调用此方法并将返回值赋值给局部变量
     *
     * @return
     */
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

    /**
     * 功能
     * 插入个人信息
     *
     * 使用方法
     * 1.传入个人信息对象
     * 2.通过返回值判断插入是否成功
     *
     * 注意
     * 1.传入个人信息对象必须携带账号
     *
     * @param information
     * @return
     */
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

    /**
     * 功能
     * 删除个人信息
     *
     * 使用方法
     * 1.传入个人信息对象
     * 2.通过返回值判断插入是否成功
     *
     * 注意
     * 1.传入个人信息对象必须携带至少一个条件
     *
     * @param condition
     * @return
     */
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

    /**
     * 功能
     * 查询个人信息
     *
     * 使用方法
     * 1.传入个人信息对象
     * 2.通过返回值索取数据
     *
     * 注意
     * 1.传入个人信息对象必须携带至少一个条件
     * 2.返回值使用前必须先进行非空判断
     *
     * @param condition
     * @return
     */
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

    /**
     * 构造方法
     */
    private OperateInformation(){
        objectOperation=JSONObjectOperation.getInstance();
        arrayOperation=JSONArrayOperation.getOperation();
        connection=HttpConnection.getHttpConnection();
        url=HttpConstant.getConstant().getURL_Information();
        method="POST";
    }

}

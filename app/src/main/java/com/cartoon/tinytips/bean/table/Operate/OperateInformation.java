package com.cartoon.tinytips.bean.table.Operate;


import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.util.JSON.JSONArrayOperation;
import com.cartoon.tinytips.util.JSON.JSONObjectOperation;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.network.HttpConstant;
import com.cartoon.tinytips.util.network.HttpConnection;
import com.cartoon.tinytips.util.network.IDataCallBack;
import com.cartoon.tinytips.util.network.IHttpConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cartoon
 * @version 1.4
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

    private IHttpConnection connection;

    private String url;

    private String method;

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
    public void add(Information information, final IOperateBean<String> operateBean){
        url=HttpConstant.getConstant().getURL_Information("add");
        JSONObject data=objectOperation.setInformationToJSON(information);
        connection.sendJSONObject(url, method, data, new IDataCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                operateBean.onSuccess(s);
            }

            @Override
            public void onFail(String msg) {
                operateBean.onFail(msg);
            }
        });
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
    public void delete(Information condition,final IOperateBean<String> operateBean){
        url=HttpConstant.getConstant().getURL_Information("delete");
        JSONObject data=objectOperation.setInformationToJSON(condition);
        connection.sendJSONObject(url, method, data, new IDataCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                operateBean.onSuccess(s);
            }

            @Override
            public void onFail(String msg) {
                operateBean.onFail(msg);
            }
        });
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
    public void query(Information condition,final IOperateBean<List<Information>> operateBean){
        url=HttpConstant.getConstant().getURL_Information("query");
        JSONObject data=objectOperation.setInformationToJSON(condition);
        connection.sendJSONObject(url, method, data, new IDataCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                if(result.equals("[]")){
                    operateBean.onFail("500");
                }
                else {
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
                    List<Information> list=new ArrayList<>();
                    if(JudgeEmpty.isNotEmpty(temp)){
                        for(JSONObject object:temp){
                            list.add(objectOperation.getInformationFromJSON(object));
                        }
                    }
                    operateBean.onSuccess(list);
                }
            }

            @Override
            public void onFail(String msg) {
                operateBean.onFail(msg);
            }
        });

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
    public void update(Information oldInformation,Information newInformation,final IOperateBean<String> operateBean){
        url=HttpConstant.getConstant().getURL_Information("update");
        JSONObject condition=objectOperation.setInformationToJSON(oldInformation);
        JSONObject data=objectOperation.setInformationToJSON(newInformation);
        List<JSONObject> temp=new ArrayList<>();
        temp.add(condition);
        temp.add(data);
        JSONArray array=arrayOperation.setObjectToArray(temp);
        connection.sendJSONArray(url, method, array, new IDataCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                operateBean.onSuccess(s);
            }

            @Override
            public void onFail(String msg) {
                operateBean.onFail(msg);
            }
        });
    }


    private OperateInformation(){
        objectOperation=JSONObjectOperation.getInstance();
        arrayOperation=JSONArrayOperation.getOperation();
        connection=new HttpConnection();
        method="POST";
    }
}

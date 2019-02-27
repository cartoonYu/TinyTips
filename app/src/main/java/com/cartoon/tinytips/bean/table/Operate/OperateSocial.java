package com.cartoon.tinytips.bean.table.Operate;

import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.table.Social;
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

public class OperateSocial {

    private static volatile OperateSocial operateSocial;

    private JSONObjectOperation objectOperation;

    private JSONArrayOperation arrayOperation;

    private IHttpConnection connection;

    private String url;

    private String method;

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
    public void add(Social social, final IOperateBean<String> operateBean){
        url=HttpConstant.getConstant().getURL_Social("add");
        JSONObject data=objectOperation.setSocialToJSON(social);
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
     * 1.传入社区对象
     * 2.通过方法isSuccess判断删除是否成功
     *
     * 注意
     * 1.传入社区对象必须携带至少一个条件
     *
     * @param condition
     * @return
     */
    public void delete(Social condition, final IOperateBean<String> operateBean){
        url=HttpConstant.getConstant().getURL_Social("delete");
        JSONObject data=objectOperation.setSocialToJSON(condition);
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
     * 查询社区信息
     *
     * 使用方法
     * 1.传入社区对象
     *
     * 注意
     * 1.传入社区对象必须携带至少一个条件
     * 2.通过方法getQueryData获取返回的社交信息集合
     *
     * @param condition
     * @return
     */
    public void query(Social condition, final IOperateBean<List<Social>> operateBean){
        url=HttpConstant.getConstant().getURL_Social("query");
        JSONObject data=objectOperation.setSocialToJSON(condition);
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
                    List<Social> list=new ArrayList<>();
                    if(JudgeEmpty.isNotEmpty(temp)){
                        for(JSONObject object:temp){
                            list.add(objectOperation.getSocialFromJSON(object));
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

    private OperateSocial(){
        objectOperation=JSONObjectOperation.getInstance();
        arrayOperation=JSONArrayOperation.getOperation();
        connection=new HttpConnection();
        method="POST";
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

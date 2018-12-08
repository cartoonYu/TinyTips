package com.cartoon.tinytips.bean.Operate;

import com.cartoon.tinytips.bean.CommentDetails;
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
 * 拆分形参传进的CommentDetails，传递给数据库操作层
 * @author cartoon
 * @version 1.0
 */

public class OperateCommentDetails {

    private static volatile OperateCommentDetails commentDetails;

    private OperateInformation operateInformation;

    private JSONObjectOperation objectOperation;

    private JSONArrayOperation arrayOperation;

    private HttpConnection connection;

    private String url;

    private String method;

    private boolean isNotFinish;

    private boolean isSuccess;

    private List<CommentDetails> queryData;

    /**
     * 功能
     * 返回本类对象，确保在程序运行的过程只有一个对象
     *
     * 使用方法
     * 1.类名直接调用获取
     *
     * @return
     */
    public static OperateCommentDetails getCommentDetails(){
        if(JudgeEmpty.isEmpty(commentDetails)){
            synchronized (OperateCommentDetails.class){
                if(JudgeEmpty.isEmpty(commentDetails)){
                    commentDetails=new OperateCommentDetails();
                }
            }
        }
        return commentDetails;
    }

    /**
     * 功能
     * 插入评论信息
     *
     * 使用方法
     * 1.传入评论信息对象
     * 2.通过方法isSuccess获取插入结果
     *
     * 注意
     * 1.传入评论对象必须携带笔记ID，用户ID，评论详情
     *
     * @param details
     * @return
     */
    public void add(CommentDetails details){
        JSONObject data=objectOperation.setCommentDetailsToJSON(details,"add");
        String result=sendData(data,null);
        setSuccess(result);
    }

    /**
     * 功能
     * 删除评论信息
     *
     * 使用方法
     * 1.传入评论对象
     * 2.通过方法isSuccess判断删除是否成功
     *
     * 注意
     * 1.传入评论对象必须携带笔记ID，用户ID，评论详情
     *
     * @param details
     * @return
     */
    public void delete(CommentDetails details){
        JSONObject data=objectOperation.setCommentDetailsToJSON(details,"delete");
        String result=sendData(data,null);
        setSuccess(result);
    }

    /**
     * 功能
     * 查询评论信息
     *
     * 使用方法
     * 1.传入评论信息对象
     *
     * 注意
     * 1.传入评论信息对象必须携带至少一个条件
     * 2.通过方法getQueryData获取返回的个人信息集合
     *
     * @param condition
     * @return
     */
    public void query(CommentDetails condition){
        JSONObject data=objectOperation.setCommentDetailsToJSON(condition,"query");
        String result=sendData(data,null);
        setQueryData(result);
    }

    /**
     * 功能
     * 更新评论信息
     *
     * 使用方法
     * 1.传入原有评论信息以及修改后的评论信息
     * 2.通过返回值判断更新是否成功
     *
     * 注意
     * 1.传入的两个评论信息必须笔记ID以及用户ID都一致
     *
     * @param oldDetails
     * @param newDetails
     * @return
     */
    public void update(CommentDetails oldDetails,CommentDetails newDetails){
        JSONObject condition=objectOperation.setCommentDetailsToJSON(oldDetails,"update");
        JSONObject data=objectOperation.setCommentDetailsToJSON(newDetails,"update");
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
    public List<CommentDetails> getQueryData(){
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
                CommentDetails details=objectOperation.getCommentDetailsFromJSON(object);
                queryData.add(details);
            }
        }
    }

    private OperateCommentDetails(){
        operateInformation=OperateInformation.getOperateInformation();
        objectOperation=JSONObjectOperation.getInstance();
        arrayOperation=JSONArrayOperation.getOperation();
        connection=HttpConnection.getConnection();
        queryData=new ArrayList<>();
        url=HttpConstant.getConstant().getURL_CommentDetails();
        method="POST";
        setNotFinish(true);
    }
}

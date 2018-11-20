package com.cartoon.tinytips.bean.Operate;

import com.cartoon.tinytips.bean.Comment;
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
 *
 * @author cartoon
 * @version 1.0
 *
 * description
 * 通过网络传输对数据库评论表进行增删查改操作
 *
 * how to use
 * 1.通过静态方法getOperateComment获取本类对象
 * 2.根据相应操作执行相应的方法
 *   add(初始化该笔记的评论统计)
 *   delete(删除该笔记的评论统计)
 *   query(查询该笔记的评论统计)
 *   update(更新该笔记的评论统计)
 * 3.因网络传输以及数据库操作需要时间，
 *   通过方法isNotFinish()判断操作是否完成（true为未完成）
 * 4.获取结果
 *   插入，删除，查询，更新操作通过方法isSuccess()获取操作结果
 *   查询操作通过方法getQueryData()获取结果
 *
 * notice
 * 1.本类无法new对象，只能通过静态方法getOperateComment获取
 */

public class OperateComment {

    private static volatile OperateComment operateComment;

    private JSONObjectOperation objectOperation;

    private JSONArrayOperation arrayOperation;

    private boolean isNotFinish;

    private boolean isSuccess;

    private Comment queryData;

    private HttpConnection connection;

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
    public static OperateComment getOperateComment(){
        if(JudgeEmpty.isEmpty(operateComment)){
            synchronized (OperateComment.class){
                if(JudgeEmpty.isEmpty(operateComment)){
                    operateComment=new OperateComment();
                }
            }
        }
        return operateComment;
    }


    /**
     * 功能
     * 查询评论信息
     *
     * 使用方法
     * 1.传入对象
     * 2.通过返回值索取数据
     *
     * 注意
     * 1.返回值使用前必须先进行非空判断
     *
     * @param noteId
     * @return
     */
    public void query(long noteId){
        Comment comment=new Comment();
        comment.setNoteId(noteId);
        JSONObject data=objectOperation.setCommentToJSON(comment,"query");
        String result=sendData(data,null);
        setQueryData(result);
    }

    /**
     * 功能
     * 更新评论信息
     *
     * 使用方法
     * 1.传入笔记id以及修改的数据及其属性
     * 2.通过返回值判断更新是否成功
     *
     * 注意
     * 1.传入的原有笔记信息对象必须携带至少一个条件
     *
     * @param noteId
     * @param name
     * @param data
     * @return
     */
    public void update(long noteId,String name,int data){
        Comment source=new Comment();
        Comment result=new Comment();
        source.setNoteId(noteId);
        result.setNoteId(noteId);
        switch (name){
            case "like":{
                result.setLike(data);
                break;
            }
            case "comment":{
                result.setComment(data);
                break;
            }
            case "collect":{
                result.setCollect(data);
                break;
            }
            case "forward":{
                result.setForward(data);
                break;
            }
        }
        List<JSONObject> list=new ArrayList<>();
        list.add(objectOperation.setCommentToJSON(source,"update"));
        list.add(objectOperation.setCommentToJSON(result,"update"));
        String temp=sendData(null,arrayOperation.setObjectToArray(list));
        setSuccess(temp);
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
    private String sendData(JSONObject object, JSONArray array){
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
    public Comment getQueryData(){
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
     * 将服务器返回的查询数据转换成bean类形式输出
     *
     * @param result
     */
    private void setQueryData(String result) {
        JSONObject object=null;
        try {
            object=new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        queryData=objectOperation.getCommentFromJSON(object);
    }

    private OperateComment(){
        objectOperation=JSONObjectOperation.getInstance();
        arrayOperation=JSONArrayOperation.getOperation();
        queryData=new Comment();
        connection=HttpConnection.getConnection();
        url=HttpConstant.getConstant().getURL_Comment();
        method="POST";
    }

}

package com.cartoon.tinytips.bean.table.Operate;

import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.table.Comment;
import com.cartoon.tinytips.util.JSON.JSONArrayOperation;
import com.cartoon.tinytips.util.JSON.JSONObjectOperation;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.network.HttpConnection;
import com.cartoon.tinytips.util.network.HttpConstant;
import com.cartoon.tinytips.util.network.IDataCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 拆分形参传进的CommentDetails，传递给数据库操作层
 * @author cartoon
 * @version 1.3
 */

public class OperateComment {

    private static volatile OperateComment commentDetails;

    private OperateInformation operateInformation;

    private JSONObjectOperation objectOperation;

    private JSONArrayOperation arrayOperation;

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
    public static OperateComment getCommentDetails(){
        if(JudgeEmpty.isEmpty(commentDetails)){
            synchronized (OperateComment.class){
                if(JudgeEmpty.isEmpty(commentDetails)){
                    commentDetails=new OperateComment();
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
     * @param comment
     * @return
     */
    public void add(Comment comment, final IOperateBean<String> operateBean){
        JSONObject data=objectOperation.setCommentToJSON(comment,"add");
        connection.sendJSONObject(url,method,data);
        connection.sendData(new IDataCallBack<String>() {
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
     * 删除评论信息
     *
     * 使用方法
     * 1.传入评论对象
     * 2.通过方法isSuccess判断删除是否成功
     *
     * 注意
     * 1.传入评论对象必须携带笔记ID，用户ID，评论详情
     *
     * @param comment
     * @return
     */
    public void delete(Comment comment, final IOperateBean<String> operateBean){
        JSONObject data=objectOperation.setCommentToJSON(comment,"delete");
        connection.sendJSONObject(url,method,data);
        connection.sendData(new IDataCallBack<String>() {
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
    public void query(Comment condition, final IOperateBean<List<Comment>> operateBean){
        JSONObject data=objectOperation.setCommentToJSON(condition,"query");
        connection.sendJSONObject(url,method,data);
        connection.sendData(new IDataCallBack<String>() {
            @Override
            public void onSuccess(String data) {
                if(data.equals("[]")){
                    operateBean.onFail("500");
                }
                else {
                    JSONArray array=null;
                    try {
                        array=new JSONArray(data);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    List<JSONObject> temp=null;
                    if(JudgeEmpty.isNotEmpty(array)){
                        temp=arrayOperation.getObjectsFromArray(array);
                    }
                    List<Comment> comments=new ArrayList<>();
                    for(JSONObject object:temp){
                        comments.add(objectOperation.getCommentFromJSON(object));
                    }
                    operateBean.onSuccess(comments);
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
    public void update(Comment oldDetails, Comment newDetails, final IOperateBean<String> operateBean){
        JSONObject condition=objectOperation.setCommentToJSON(oldDetails,"update");
        JSONObject data=objectOperation.setCommentToJSON(newDetails,"update");
        List<JSONObject> temp=new ArrayList<>();
        temp.add(condition);
        temp.add(data);
        JSONArray array=arrayOperation.setObjectToArray(temp);
        connection.sendJSONArray(url,method,array);
        connection.sendData(new IDataCallBack<String>() {
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


    private OperateComment(){
        operateInformation=OperateInformation.getOperateInformation();
        objectOperation=JSONObjectOperation.getInstance();
        arrayOperation=JSONArrayOperation.getOperation();
        connection=HttpConnection.getConnection();
        url=HttpConstant.getConstant().getURL_Comment();
        method="POST";
    }
}

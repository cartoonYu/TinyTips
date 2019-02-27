package com.cartoon.tinytips.bean.table.Operate;

import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.table.Comment;
import com.cartoon.tinytips.util.JSON.JSONObjectOperation;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.network.HttpConstant;
import com.cartoon.tinytips.util.network.HttpConnection;
import com.cartoon.tinytips.util.network.IDataCallBack;
import com.cartoon.tinytips.util.network.IHttpConnection;

import org.json.JSONObject;

/**
 * 拆分形参传进的CommentDetails，传递给数据库操作层
 * @author cartoon
 * @version 1.3
 */

public class OperateComment {

    private static volatile OperateComment commentDetails;

    private JSONObjectOperation objectOperation;

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
    public static OperateComment getComment(){
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
        url=HttpConstant.getConstant().getURL_Comment("add");
        JSONObject data=objectOperation.setCommentToJSON(comment);
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
        url=HttpConstant.getConstant().getURL_Comment("delete");
        JSONObject data=objectOperation.setCommentToJSON(comment);
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

    private OperateComment(){
        objectOperation=JSONObjectOperation.getInstance();
        connection=new HttpConnection();
        method="POST";
    }
}

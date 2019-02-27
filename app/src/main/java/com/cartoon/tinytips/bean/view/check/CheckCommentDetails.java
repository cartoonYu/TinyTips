package com.cartoon.tinytips.bean.view.check;


import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.view.CommentDetails;
import com.cartoon.tinytips.util.JSON.JSONArrayOperation;
import com.cartoon.tinytips.util.JSON.JSONObjectOperation;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.file.FileOperation;
import com.cartoon.tinytips.util.network.HttpConstant;
import com.cartoon.tinytips.util.network.HttpConnection;
import com.cartoon.tinytips.util.network.IDataCallBack;
import com.cartoon.tinytips.util.network.IHttpConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 评论详情视图查看
 * @author cartoon
 * @version 1.0
 */

public class CheckCommentDetails {

    private static volatile CheckCommentDetails checkCommentDetails;

    private JSONObjectOperation objectOperation;

    private JSONArrayOperation arrayOperation;

    private IHttpConnection connection;

    private String url;

    private String method;

    private FileOperation fileOperation;

    public void query(CommentDetails details, final IOperateBean<List<CommentDetails>> operateBean){
        url=HttpConstant.getConstant().getURL_CommentDetails("query");
        connection.sendJSONObject(url, method,
                objectOperation.setCommentDetailsToJSON(details), new IDataCallBack<String>() {
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
                    List<CommentDetails> list=new ArrayList<>();
                    if(JudgeEmpty.isNotEmpty(temp)){
                        for(JSONObject object:temp){
                            objectOperation.displayJSON(object);
                            CommentDetails commentDetails=objectOperation.getCommentDetailsFromJSON(object);
                            commentDetails=getHeadPro(commentDetails);
                            list.add(commentDetails);
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
     * 转换头像类型
     * @param commentDetails
     * @return
     */
    private CommentDetails getHeadPro(CommentDetails commentDetails){
        String resource=commentDetails.getHeadPortraitResource();
        String fileName=commentDetails.getHeadPortraitName();
        File result=fileOperation.transStringToFile(resource,fileName);
        commentDetails.setHeadPortrait(result);
        return commentDetails;
    }

    private CheckCommentDetails(){
        objectOperation=JSONObjectOperation.getInstance();
        arrayOperation=JSONArrayOperation.getOperation();
        connection=new HttpConnection();
        method="POST";
        fileOperation=FileOperation.getOperation();
    }

    public static CheckCommentDetails getCheckCommentDetails(){
        if(JudgeEmpty.isEmpty(checkCommentDetails)){
            synchronized (CheckCommentDetails.class){
                if(JudgeEmpty.isEmpty(checkCommentDetails)){
                    checkCommentDetails=new CheckCommentDetails();
                }
            }
        }
        return checkCommentDetails;
    }
}

package com.cartoon.tinytips.Note.Comment;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Comment;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.bean.table.Local.LocalInformation;
import com.cartoon.tinytips.bean.table.Note;
import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.table.Operate.OperateComment;
import com.cartoon.tinytips.bean.table.Operate.OperateInformation;
import com.cartoon.tinytips.bean.table.Operate.imp.IOperateComment;
import com.cartoon.tinytips.bean.view.CommentDetails;
import com.cartoon.tinytips.bean.view.StatSocial;
import com.cartoon.tinytips.bean.view.check.CheckCommentDetails;
import com.cartoon.tinytips.bean.view.check.imp.ICheckCommentDetails;
import com.cartoon.tinytips.util.Adapters.Comment.CommentItem;
import com.cartoon.tinytips.util.JudgeEmpty;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CommentModel implements IComment.Model {

    private IOperateComment operateComment;

    private ICheckCommentDetails checkCommentDetails;

    private Information localInformation;

    @Override
    public void getComment(StatSocial social, final ValueCallBack<List<CommentDetails>> callBack) {
        if(JudgeEmpty.isEmpty(social)){
            callBack.onFail("系统错误");
        }
        CommentDetails commentDetails=new CommentDetails();
        commentDetails.setNoteId(social.getNoteId());
        checkCommentDetails.query(commentDetails, new IOperateBean<List<CommentDetails>>() {
            @Override
            public void onSuccess(List<CommentDetails> commentDetails) {
                callBack.onSuccess(commentDetails);
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail(msg);
            }
        });
    }

    @Override
    public void addComment(final Comment details, final ValueCallBack<String> callBack) {
        details.setUserId(localInformation.getId());
        operateComment.add(details, new IOperateBean<String>() {
            @Override
            public void onSuccess(String s) {
                callBack.onSuccess("评论成功");
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail(msg);
            }
        });
    }

    public CommentModel(){
        operateComment =OperateComment.getComment();
        localInformation=LocalInformation.getLocalInformation().query();
        checkCommentDetails=CheckCommentDetails.getCheckCommentDetails();
    }
}

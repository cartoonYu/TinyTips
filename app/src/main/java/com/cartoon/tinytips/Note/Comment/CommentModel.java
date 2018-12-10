package com.cartoon.tinytips.Note.Comment;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.CommentDetails;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Local.LocalInformation;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Operate.OperateCommentDetails;
import com.cartoon.tinytips.bean.Operate.OperateInformation;
import com.cartoon.tinytips.util.Adapters.Comment.CommentItem;
import com.cartoon.tinytips.util.JudgeEmpty;

import java.util.ArrayList;
import java.util.List;

public class CommentModel implements IComment.Model {

    private OperateCommentDetails operateCommentDetails;

    private OperateInformation operateInformation;

    private Information localInformation;

    @Override
    public void getComment(Note note, ValueCallBack<List<CommentItem>> callBack) {
        CommentDetails commentDetails=new CommentDetails();
        commentDetails.setNoteId(note.getId());
        operateCommentDetails.query(commentDetails);
        while (operateCommentDetails.isNotFinish()){
        }
        List<CommentDetails> list=operateCommentDetails.getQueryData();
        if(JudgeEmpty.isEmpty(list)){
            callBack.onFail("获取评论失败，请重试");
        }
        else {
            List<CommentItem> commentItems=new ArrayList<>();
            for(CommentDetails details:list){
                CommentItem commentItem=new CommentItem();
                commentItem.setContent(details.getDetails());
                commentItem.setTime(details.getDate());

                Information information=new Information();
                information.setId(details.getUserId());
                operateInformation.query(information);
                while (operateInformation.isNotFinish()){
                }
                List<Information> informationList=operateInformation.getQueryData();
                commentItem.setUserImage(informationList.get(0).getHeadPortrait());
                commentItem.setUsername(informationList.get(0).getNickName());
                commentItems.add(commentItem);
            }
            callBack.onSuccess(commentItems);
        }
    }

    @Override
    public void addComment(CommentDetails details, ValueCallBack<CommentItem> callBack) {
        details.setUserId(localInformation.getId());
        operateCommentDetails.add(details);
        while (operateCommentDetails.isNotFinish()){
        }
        if(operateCommentDetails.isSuccess()){
            operateCommentDetails.query(details);
            while (operateCommentDetails.isNotFinish()){
            }
            CommentDetails commentDetails=operateCommentDetails.getQueryData().get(0);

            CommentItem item=new CommentItem();
            item.setTime(commentDetails.getDate());
            item.setUsername(localInformation.getNickName());
            item.setContent(commentDetails.getDetails());
            item.setUserImage(localInformation.getHeadPortrait());
            callBack.onSuccess(item);
        }
        else {
            callBack.onFail("评论失败，请重试");
        }
    }

    public CommentModel(){
        operateCommentDetails=OperateCommentDetails.getCommentDetails();
        operateInformation=OperateInformation.getOperateInformation();
        localInformation=LocalInformation.getLocalInformation().query();
    }
}

package com.cartoon.tinytips.Note.Comment;

import com.cartoon.tinytips.R;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.util.Adapters.Comment.CommentItem;

import java.util.ArrayList;

public class CommentModel implements IComment.Model {
    private  ArrayList<CommentItem> comments;

    @Override
    public void getComments(ValueCallBack<ArrayList<CommentItem>> callBack) {
        comments.add(new CommentItem(R.drawable.apple,"asd","asd","3小时前"));
        if(comments.isEmpty()){
            callBack.onFail("获取内容失败");
        }
        else {
            callBack.onSuccess(comments);
        }
    }
}

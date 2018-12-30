package com.cartoon.tinytips.Note.Comment;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Comment;
import com.cartoon.tinytips.bean.table.Note;
import com.cartoon.tinytips.bean.view.CommentDetails;
import com.cartoon.tinytips.bean.view.StatSocial;
import com.cartoon.tinytips.util.Adapters.Comment.CommentItem;

import java.util.List;

public interface IComment {

    interface View{
        void recyclerList(List<CommentDetails> list);
        void initComment();
    }

    interface Presenter{
        void initComment(StatSocial social);
        void addComment(String details,StatSocial social);
    }

    interface Model{
        void getComment(StatSocial social, ValueCallBack<List<CommentDetails>> callBack);
        void addComment(Comment details, ValueCallBack<String> callBack);
    }

}

package com.cartoon.tinytips.Note.Comment;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.CommentDetails;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.util.Adapters.Comment.CommentItem;

import java.util.List;

interface IComment {
    interface View{
        void recyclerList(List<CommentItem> list);
        void addComment(CommentItem item);
    }

    interface Presenter{
        void initComment(Note note);
        void addComment(String details,Note note);
    }

    interface Model{
        void getComment(Note note, ValueCallBack<List<CommentItem>> callBack);
        void addComment(CommentDetails details,ValueCallBack<CommentItem> callBack);
    }
}

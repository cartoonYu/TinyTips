package com.cartoon.tinytips.Note.Comment;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.util.Adapters.Comment.CommentItem;

import java.util.ArrayList;

interface IComment {
    interface View{
        void recyclerList(ArrayList<CommentItem> commentItems);
    }

    interface Presenter{
        void  handleDate();
    }

    interface Model{
        void getComments(ValueCallBack<ArrayList<CommentItem>> callBack);
    }
}

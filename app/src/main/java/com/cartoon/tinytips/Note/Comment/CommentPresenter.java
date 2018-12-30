package com.cartoon.tinytips.Note.Comment;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Comment;
import com.cartoon.tinytips.bean.table.Note;
import com.cartoon.tinytips.bean.view.CommentDetails;
import com.cartoon.tinytips.bean.view.StatSocial;
import com.cartoon.tinytips.util.Adapters.Comment.CommentItem;
import com.cartoon.tinytips.util.ShowToast;

import java.util.List;

public class  CommentPresenter extends BaseActivityPresenter<com.cartoon.tinytips.Note.Comment.Comment> implements IComment.Presenter {

    private IComment.View view;

    private IComment.Model model;

    @Override
    public void initComment(StatSocial social) {
        model.getComment(social, new ValueCallBack<List<CommentDetails>>() {
            @Override
            public void onSuccess(List<CommentDetails> commentItems) {
                view.recyclerList(commentItems);
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });
    }

    @Override
    public void addComment(String details,StatSocial social) {
        Comment comment=new Comment();
        comment.setDetails(details);
        comment.setNoteId(social.getNoteId());
        model.addComment(comment, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                ShowToast.shortToast(s);
                view.initComment();
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });
    }

    public  CommentPresenter(IComment.View view){
        this.view=view;
        this.model = new CommentModel();
    }

    @Override
    protected void deleteView(){
        view=null;
        model = null;
    }

}

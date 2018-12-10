package com.cartoon.tinytips.Note.Comment;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.CommentDetails;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.util.Adapters.Comment.CommentItem;
import com.cartoon.tinytips.util.ShowToast;

import java.util.List;

public class  CommentPresenter extends BaseActivityPresenter<Comment> implements IComment.Presenter {

    private IComment.View view;

    private IComment.Model model;

    @Override
    public void initComment(Note note) {
        model.getComment(note, new ValueCallBack<List<CommentItem>>() {
            @Override
            public void onSuccess(List<CommentItem> commentItems) {
                view.recyclerList(commentItems);
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });
    }

    @Override
    public void addComment(String details,Note note) {
        CommentDetails commentDetails=new CommentDetails();
        commentDetails.setDetails(details);
        commentDetails.setNoteId(note.getId());
        model.addComment(commentDetails, new ValueCallBack<CommentItem>() {
            @Override
            public void onSuccess(CommentItem commentItem) {
                view.addComment(commentItem);
                ShowToast.shortToast("评论成功");
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

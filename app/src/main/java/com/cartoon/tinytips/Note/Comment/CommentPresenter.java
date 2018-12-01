package com.cartoon.tinytips.Note.Comment;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Default_many;
import com.cartoon.tinytips.util.Adapters.Comment.CommentItem;
import com.cartoon.tinytips.util.ShowToast;

import java.util.ArrayList;

public class  CommentPresenter extends BaseActivityPresenter<Comment> implements IComment.Presenter {
    private IComment.View view;

    private IComment.Model model;

    public  CommentPresenter(IComment.View view){
        this.view=view;
        this.model = new CommentModel();
    }

    @Override
    protected void deleteView(){
        view=null;
        model = null;
    }

    @Override
    public void handleDate() {
        model.getComments(new ValueCallBack<ArrayList<CommentItem >>(){
            @Override
            public void onSuccess(ArrayList<CommentItem> commentItems){
               view.recyclerList(commentItems);
            }
            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });
    }
}

package com.cartoon.tinytips.Note.Comment;

import com.cartoon.tinytips.BaseActivityPresenter;

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

}

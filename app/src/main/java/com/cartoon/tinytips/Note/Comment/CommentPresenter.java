package com.cartoon.tinytips.Note.Comment;

import com.cartoon.tinytips.BaseActivityPresenter;

public class  CommentPresenter extends BaseActivityPresenter<Comment> implements IComment.Presenter {
    private IComment.View view;

    public  CommentPresenter(IComment.View view){
        this.view=view;
    }

    @Override
    protected void deleteView(){
        view=null;
    }

}

package com.cartoon.tinytips.Message.CommentMessage;

import com.cartoon.tinytips.BaseActivityPresenter;

public class CommentMessagePresenter extends BaseActivityPresenter<CommentMessage> implements ICommentMessage.Presenter{
    private ICommentMessage.View view;
    public CommentMessagePresenter(ICommentMessage.View view){
        this.view=view;
    }
    @Override
    protected void deleteView(){
        view=null;
    }
}

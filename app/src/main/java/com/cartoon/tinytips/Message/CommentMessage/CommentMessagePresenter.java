package com.cartoon.tinytips.Message.CommentMessage;

import com.cartoon.tinytips.BaseActivityPresenter;

public class CommentMessagePresenter extends BaseActivityPresenter<CommentMessage> implements ICommentMessage.Presenter{
    private ICommentMessage.View view;
    private ICommentMessage.Model model;

    public CommentMessagePresenter(ICommentMessage.View view){
        this.view=view;
        this.model = new CommentMessageModel();
    }
    @Override
    protected void deleteView(){
        view=null;
        model = null;
    }
}

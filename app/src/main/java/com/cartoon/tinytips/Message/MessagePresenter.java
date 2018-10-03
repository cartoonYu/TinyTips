package com.cartoon.tinytips.Message;

import com.cartoon.tinytips.BaseFragmentPresenter;

class MessagePresenter extends BaseFragmentPresenter<Message> implements IMessage.Presenter{
    private IMessage.View view;
    private IMessage.Model model;

    public MessagePresenter(IMessage.View view){
        this.view=view;
        this.model = new MessageModel();
    }
    @Override
    protected void deleteView(){
        view=null;
        model = null;
    }
}

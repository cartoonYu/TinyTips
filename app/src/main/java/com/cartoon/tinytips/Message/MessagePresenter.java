package com.cartoon.tinytips.Message;

import com.cartoon.tinytips.BaseFragmentPresenter;

class MessagePresenter extends BaseFragmentPresenter<Message> implements IMessage.Presenter{
    private IMessage.View view;
    public MessagePresenter(IMessage.View view){
        this.view=view;
    }
    @Override
    protected void deleteView(){
        view=null;
    }
}

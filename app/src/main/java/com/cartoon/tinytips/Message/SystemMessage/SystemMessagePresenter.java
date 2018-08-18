package com.cartoon.tinytips.Message.SystemMessage;

import com.cartoon.tinytips.BaseActivityPresenter;

public class SystemMessagePresenter extends BaseActivityPresenter<SystemMessage> implements ISystemMessage.Presenter{
    private ISystemMessage.View view;
    public SystemMessagePresenter(ISystemMessage.View view){
        this.view=view;
    }
    @Override
    protected void deleteView(){
        view=null;
    }
}

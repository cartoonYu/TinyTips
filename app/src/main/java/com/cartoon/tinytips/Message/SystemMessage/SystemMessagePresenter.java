package com.cartoon.tinytips.Message.SystemMessage;

import com.cartoon.tinytips.BaseActivityPresenter;

public class SystemMessagePresenter extends BaseActivityPresenter<SystemMessage> implements ISystemMessage.Presenter{
    private ISystemMessage.View view;
    private ISystemMessage.Model model;

    public SystemMessagePresenter(ISystemMessage.View view){
        this.view=view;
        this.model = new SystemMessageModel();
    }
    @Override
    protected void deleteView(){
        view=null;
        model = null;
    }
}

package com.cartoon.tinytips.Message.FlowingMessage;

import com.cartoon.tinytips.BaseActivityPresenter;

public class FlowingMessagePresenter extends BaseActivityPresenter<FlowingMessage> implements IFlowingMessage.Presenter {
    private IFlowingMessage.View view;
    private IFlowingMessage.Model model;

    public FlowingMessagePresenter(IFlowingMessage.View view){
        this.view=view;
        this.model = new FlowingMessageModel();
    }
    @Override
    protected void deleteView(){
        view=null;
        model = null;
    }
}

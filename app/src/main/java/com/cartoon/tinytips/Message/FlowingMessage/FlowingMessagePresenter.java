package com.cartoon.tinytips.Message.FlowingMessage;

import com.cartoon.tinytips.BaseActivityPresenter;

public class FlowingMessagePresenter extends BaseActivityPresenter<FlowingMessage> implements IFlowingMessage.Presenter {
    private IFlowingMessage.View view;
    public FlowingMessagePresenter(IFlowingMessage.View view){
        this.view=view;
    }
    @Override
    protected void deleteView(){
        view=null;
    }
}

package com.cartoon.tinytips.Message;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.R;

public class Message extends BaseFragment<MessagePresenter> implements IMessage.View {
    private MessagePresenter presenter;

    @Override
    protected MessagePresenter initPresent(){
        presenter=new MessagePresenter(this);
        return presenter;
    }

    @Override
    protected int getLayout(){
        return R.layout.message;
    }

    @Override
    protected void initView(){

    }

    @Override
    protected void onPrepare(){

    }
}

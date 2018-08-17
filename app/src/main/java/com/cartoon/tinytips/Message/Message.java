package com.cartoon.tinytips.Message;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.UI.RevampStatusBar;
import com.cartoon.tinytips.util.UI.RevampToolbar;

import butterknife.BindView;

public class Message extends BaseFragment<MessagePresenter> implements IMessage.View {

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @Override
    protected MessagePresenter initPresent(){
        return new MessagePresenter(this);
    }

    @Override
    protected int getLayout(){
        return R.layout.message;
    }

    @Override
    protected void initView(){
        revampStatusBar();
    }

    @Override
    protected void onPrepare(){

    }
    @Override
    public void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }

    @Override
    public void revampToolbar(){
        RevampToolbar.setText(toolbarText,new String("消息"));
    }

}

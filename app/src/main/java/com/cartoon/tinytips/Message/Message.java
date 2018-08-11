package com.cartoon.tinytips.Message;

import android.view.View;
import android.widget.RelativeLayout;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.UI.RevampStatusBar;

import butterknife.BindView;

public class Message extends BaseFragment<MessagePresenter> implements IMessage.View {

    @BindView(R.id.statusBar)
    View statusBar;

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
        RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)statusBar.getLayoutParams();
        params.width=RelativeLayout.LayoutParams.MATCH_PARENT;
        params.height= RevampStatusBar.getStatusBar(getContext());
        statusBar.setLayoutParams(params);
        statusBar.setBackgroundColor(getResources().getColor(R.color.white));
    }
}

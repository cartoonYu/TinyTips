package com.cartoon.tinytips.Message;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.Message.CommentMessage.CommentMessage;
import com.cartoon.tinytips.Message.FavoriteMessage.FavoriteMessage;
import com.cartoon.tinytips.Message.FlowingMessage.FlowingMessage;
import com.cartoon.tinytips.Message.SystemMessage.SystemMessage;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.UI.RevampStatusBar;
import com.cartoon.tinytips.util.UI.RevampToolbar;

import butterknife.BindView;
import butterknife.OnClick;

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
        revampToolbar();
    }

    @Override
    protected void onPrepare(){

    }

    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }

    private void revampToolbar(){
        RevampToolbar.setText(toolbarText,new String("消息"));
    }


    @OnClick(R.id.systemMassage)
    public void onClickSystemMessage(){
        IntentActivity.intentWithoutData(getContext(),SystemMessage.class);
        IntentActivity.finishActivity(getActivity());
    }

    @OnClick(R.id.commentMassage)
    public void onClickcommentMessage(){
        IntentActivity.intentWithoutData(getContext(),CommentMessage.class);
        IntentActivity.finishActivity(getActivity());
    }

    @OnClick(R.id.flowingMassage)
    public void onClickFlowingMessage(){
        IntentActivity.intentWithoutData(getContext(),FlowingMessage.class);
        IntentActivity.finishActivity(getActivity());
    }

    @OnClick(R.id.favoriteMassage)
    public void onClickFavoriteMessage(){
        IntentActivity.intentWithoutData(getContext(),FavoriteMessage.class);
        IntentActivity.finishActivity(getActivity());
    }

}

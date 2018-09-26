package com.cartoon.tinytips.Message.SystemMessage;

import android.view.View;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.FragmentConstant;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.UI.RevampStatusBar;
import com.cartoon.tinytips.util.UI.RevampToolbar;

import butterknife.BindView;
import butterknife.OnClick;

public class SystemMessage extends BaseActivity<SystemMessagePresenter> implements ISystemMessage.View {
    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.toolbarBack)
    TextView back;


    @Override
    protected SystemMessagePresenter initPresent() {
        return new SystemMessagePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.messagecontent;
    }

    @Override
    protected void initView() {
        RevampToolbar.setBack(back);
        revampStatusBar();
        revampToolbar();
    }

    @Override
    protected void onPrepare() {

    }

    @OnClick(R.id.toolbarBack)
    public void onClickBack(){
        IntentActivity.intentWithData(this,Main.class,new String("main"), FragmentConstant.getConstant().getMessage());
        IntentActivity.finishActivity(this);
    }

    @Override
    public void onBackPressed(){
        IntentActivity.intentWithData(this,Main.class,new String("main"), FragmentConstant.getConstant().getMessage());
        IntentActivity.finishActivity(this);
    }


    private void revampStatusBar() {
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }


    private void revampToolbar(){
        RevampToolbar.setText(toolbarText,new String("系统消息"));
    }
}

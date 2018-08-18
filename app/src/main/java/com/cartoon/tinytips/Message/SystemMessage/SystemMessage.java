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

public class SystemMessage extends BaseActivity<SystemMessagePresenter> implements ISystemMessage.View {
    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.statusBar)
    View statusBar;

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
        revampStatusBar();
        revampToolbar();
    }

    @Override
    protected void onPrepare() {

    }
    @Override
    public void onBackPressed(){
        IntentActivity.intentWithData(this,Main.class,new String("main"), FragmentConstant.message);
    }

    @Override
    public void revampStatusBar() {
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }

    @Override
    public void revampToolbar(){
        RevampToolbar.setText(toolbarText,new String("系统消息"));
    }
}

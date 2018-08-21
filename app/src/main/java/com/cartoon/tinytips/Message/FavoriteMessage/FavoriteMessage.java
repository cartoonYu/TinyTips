package com.cartoon.tinytips.Message.FavoriteMessage;

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

public class FavoriteMessage extends BaseActivity<FavoriteMessagePresenter> implements IFavoriteMessage.View{
    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.toolbarBack)
    TextView back;

    @Override
    protected FavoriteMessagePresenter initPresent() {
        return new FavoriteMessagePresenter(this);
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
        IntentActivity.intentWithData(this,Main.class,new String("main"), FragmentConstant.message);
        IntentActivity.finishActivity(this);
    }

    @Override
    public void onBackPressed(){
        IntentActivity.intentWithData(this,Main.class,new String("main"), FragmentConstant.message);
        IntentActivity.finishActivity(this);
    }


    private void revampStatusBar() {
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }

    private void revampToolbar(){
        RevampToolbar.setText(toolbarText,new String("收到的赞"));
    }
}

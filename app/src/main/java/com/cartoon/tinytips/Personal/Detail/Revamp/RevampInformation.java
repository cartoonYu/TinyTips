package com.cartoon.tinytips.Personal.Detail.Revamp;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Personal.Detail.Detail;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.UI.RevampStatusBar;
import com.cartoon.tinytips.util.UI.RevampToolbar;

import butterknife.BindView;
import butterknife.OnClick;

public class RevampInformation extends BaseActivity<RevampInformationPresenter> implements IRevampInformation.View {

    private String revampType;    //修改的数据类型

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.personal_detail_revamp_toolbarBack)
    TextView back;

    @BindView(R.id.personal_detail_revamp_toolbarText)
    TextView toolbarText;

    @BindView(R.id.personal_detail_revamp_toolbarMenu)
    TextView toolbarMenu;

    @BindView(R.id.personal_detail_revamp_toolbar_menubutton_bg)
    RelativeLayout menuButton;

    @BindView(R.id.personal_detail_revamp)
    EditText input;

    @Override
    protected RevampInformationPresenter initPresent() {
        return new RevampInformationPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.personal_detail_revamp;
    }

    @Override
    protected void initView() {
        revampStatusBar();
        revampToolbar();
    }


    @Override
    protected void onPrepare() {
        getRevampType();
        presenter.initUI(revampType);
    }

    @OnClick(R.id.personal_detail_revamp_toolbar_menubutton_bg)
    public void onClickConfirm(){
        //确认按钮点击事件
        presenter.revampInformation(revampType);
    }

    @Override
    public void setToolBarText(String text) {
        this.toolbarText.setText(text);
    }

    @Override
    public String getInput() {
        return input.getText().toString();
    }

    private void getRevampType() {
        revampType=IntentActivity.getIntentData(this,"RevampType");
    }

    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }

    private void revampToolbar(){
        RevampToolbar.setBack(back);
        toolbarMenu.setText("确认");
        menuButton.setBackground(getDrawable(R.mipmap.menu_button));
    }

    @OnClick(R.id.personal_detail_revamp_toolbarBack)
    public void onClickBack(){
        intentToDetails();
    }

    @Override
    public void onBackPressed(){
        intentToDetails();
    }

    @Override
    public void intentToDetails() {
        IntentActivity.intentWithoutData(this,Detail.class);
        IntentActivity.finishActivity(this);
    }
}

package com.cartoon.tinytips.Note.Details;

import android.view.View;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.UI.RevampStatusBar;
import com.cartoon.tinytips.util.UI.RevampToolbar;

import butterknife.BindView;
import butterknife.OnClick;

public class NoteDetail extends BaseActivity<NoteDetailPresenter> implements INoteDetail.View{

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.toolbarBack)
    TextView back;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @Override
    protected NoteDetailPresenter initPresent(){
        return new NoteDetailPresenter(this);
    }

    @Override
    protected int getLayout(){
        return R.layout.note_detail;
    }

    @Override
    protected void initView(){
        revampStatusBar();
        initToolbar();
    }

    @Override
    protected void onPrepare(){

    }

    @OnClick(R.id.toolbarBack)
    protected void onClickBack(){
        IntentActivity.finishActivity(this);
    }

    @Override
    public void onBackPressed(){
        IntentActivity.finishActivity(this);
    }

    /**
     * 沉浸式任务栏
     */
    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }

    /**
     * 修改toolbar显示
     */
    private void initToolbar(){
        RevampToolbar.setBack(back);
        RevampToolbar.setText(toolbarText,new String("详情"));
    }

}

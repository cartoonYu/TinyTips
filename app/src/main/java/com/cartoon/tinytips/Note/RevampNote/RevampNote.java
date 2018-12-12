package com.cartoon.tinytips.Note.RevampNote;


import android.text.SpannableString;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.UI.RevampStatusBar;
import com.cartoon.tinytips.util.UI.RevampToolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class RevampNote extends BaseActivity<RevampNotePresenter> implements IRevampNote.View{

    private Note note;

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.revampNote_toolbarBack)
    TextView back;

    @BindView(R.id.revampNote_toolbarText)
    TextView toolbarText;

    @BindView(R.id.revampNote_toolbarMenu)
    TextView toolbarMenu;

    @BindView(R.id.revampNote_toolbar_menubutton_bg)
    RelativeLayout menuButton;

    @BindView(R.id.addNote_addTitle)
    EditText title;

    @BindView(R.id.contentbar)
    EditText content;

    @Override
    protected RevampNotePresenter initPresent() {
        presenter=new RevampNotePresenter(this);
        return presenter;
    }

    @Override
    protected int getLayout() {
        return R.layout.revampnote;
    }

    @Override
    protected void initView() {
        revampStatusBar();
        revampToolbar();
    }

    @Override
    protected void onPrepare() {
        getNote();
        presenter.initData(note);
    }

    @OnClick(R.id.revampNote_toolbar_menubutton_bg)
    public void onClickFinish(){
        //点击完成按钮
        presenter.revampNote(note);
    }


    @OnClick(R.id.revampNote_toolbarBack)
    public void onClickBack(){
        //点击返回按钮
        finishActivity();
    }

    @Override
    public String getNoteTitle() {
        return title.getText().toString();
    }

    @Override
    public String getContent() {
        return content.getText().toString();
    }

    @Override
    public void setTitle(String title) {
        this.title.setText(title);
    }

    @Override
    public void setContent(List<SpannableString> list) {
        for(SpannableString s:list){
            this.content.append(s);
        }
    }

    private void revampStatusBar(){
        RevampToolbar.setBack(back);
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
        toolbarMenu.setText("完成");
        menuButton.setBackground(getDrawable(R.mipmap.menu_button));
    }

    private void revampToolbar(){
        RevampToolbar.setText(toolbarText,new String("修改笔记"));
    }

    private void getNote() {
        note=IntentActivity.getIntentNote(this,"note");
    }

    @Override
    public void onBackPressed(){
        finishActivity();
    }

    @Override
    public void finishActivity() {
        IntentActivity.finishActivity(this);
    }
}

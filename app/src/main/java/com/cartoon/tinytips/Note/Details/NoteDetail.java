package com.cartoon.tinytips.Note.Details;

import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.Note.DivideNote;
import com.cartoon.tinytips.util.UI.RevampStatusBar;
import com.cartoon.tinytips.util.UI.RevampToolbar;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;

public class NoteDetail extends BaseActivity<NoteDetailPresenter> implements INoteDetail.View{

    private Note note;

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.toolbarBack)
    TextView back;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.note_detail_title)
    TextView title;

    @BindView(R.id.note_detail_contents)
    TextView details;

    @BindView(R.id.note_detail_date)
    TextView date;

    @BindView(R.id.note_detail_like)
    TextView like;

    @BindView(R.id.note_detail_comment)
    TextView comment;

    @BindView(R.id.note_detail_forward)
    TextView forward;

    @BindView(R.id.note_detail_collect)
    TextView collect;

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
    public void setTitle(){
        this.title.setText(note.getTitle());
    }

    @Override
    public void setDetails(){
        List<SpannableString> stringList=DivideNote.getDivideNote().transNoteToString(note);
        for(SpannableString string:stringList){
            this.details.append(string);
        }
    }

    @Override
    public void setLike(int num) {
        like.setText(Integer.toString(num));
    }

    @Override
    public void setComment(int num) {
        comment.setText(Integer.toString(num));
    }

    @Override
    public void setForward(int num) {
        forward.setText(Integer.toString(num));
    }

    @Override
    public void setCollect(int num) {
        collect.setText(Integer.toString(num));
    }

    @Override
    public void setLike(boolean isClick) {
        if(isClick){
            like.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.drawable.favorite_press),null,null,null);
        }
    }

    @Override
    public void setComment(boolean isClick) {
        comment.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.drawable.commentmassage),null,null,null);
    }

    @Override
    public void setForward(boolean isClick) {

    }

    @Override
    public void setCollect(boolean isClick) {
        collect.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.drawable.mycollection_press),null,null,null);
    }

    @Override
    protected void onPrepare(){
        initData();
    }

    private void initData() {
        if(JudgeEmpty.isNotEmpty(IntentActivity.getIntentNote(this,"note"))){
            note=IntentActivity.getIntentNote(this,"note");
        }
        setTitle();
        setDetails();
        setDate();
        presenter.initSocial(note);
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
    private void initToolbar() {
        RevampToolbar.setBack(back);
        RevampToolbar.setText(toolbarText,new String("详情"));
    }

    @Override
    public void setDate(){
        this.date.setText(note.getDate());
    }

}

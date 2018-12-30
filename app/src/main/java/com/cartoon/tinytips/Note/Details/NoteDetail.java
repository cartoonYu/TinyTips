package com.cartoon.tinytips.Note.Details;

import android.text.SpannableString;
import android.view.View;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.table.Note;
import com.cartoon.tinytips.bean.view.StatSocial;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.Note.DivideNote;
import com.cartoon.tinytips.util.UI.RevampStatusBar;
import com.cartoon.tinytips.util.UI.RevampToolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NoteDetail extends BaseActivity<NoteDetailPresenter> implements INoteDetail.View{

    private StatSocial social;

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
    protected void onPrepare(){
        initData();
    }

    private void initData() {
        if(JudgeEmpty.isNotEmpty(IntentActivity.getIntentStatSocial(this,"social"))){
            social=IntentActivity.getIntentStatSocial(this,"social");
            presenter.handleStatSocial(social);
        }
        if(JudgeEmpty.isNotEmpty(IntentActivity.getIntentNote(this,"note"))){
            note=IntentActivity.getIntentNote(this,"note");
            presenter.handleNote(note);
        }

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
    public void setNote(Note note) {
        this.note = note;
    }

    @Override
    public void setSocial(StatSocial social) {
        this.social = social;
    }

    @Override
    public void setTitle(String title){
        this.title.setText(title);
    }

    @Override
    public void setDetails(Note noteDetail){
        List<SpannableString> stringList=DivideNote.getDivideNote().transNoteToString(noteDetail);
        for(SpannableString string:stringList){
            this.details.append(string);
        }
    }

    @Override
    public void setDate(String date){
        this.date.setText(date);
    }

    @Override
    public void setCollect(int num, boolean isClick) {
        collect.setText(Integer.toString(num));
        if(isClick){
            collect.setCompoundDrawablesRelativeWithIntrinsicBounds
                    (getResources().getDrawable(R.drawable.mycollection_press),null,null,null);
        }
    }

    @Override
    public void setLove(int num, boolean isClick) {
        like.setText(Integer.toString(num));
        if(isClick){
            like.setCompoundDrawablesRelativeWithIntrinsicBounds
                    (getResources().getDrawable(R.drawable.favorite_press),null,null,null);
        }
    }

    @Override
    public void setForward(int num, boolean isClick) {
        forward.setText(Integer.toString(num));
    }

    @Override
    public void setComment(int num, boolean isClick) {
        comment.setText(Integer.toString(num));
    }
}

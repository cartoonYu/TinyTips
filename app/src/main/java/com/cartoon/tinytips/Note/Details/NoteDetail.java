package com.cartoon.tinytips.Note.Details;

import android.text.SpannableString;
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
        if(JudgeEmpty.isNotEmpty(IntentActivity.getIntentNote(this,"note"))){
            note=IntentActivity.getIntentNote(this,"note");
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
    private void initToolbar(){
        RevampToolbar.setBack(back);
        RevampToolbar.setText(toolbarText,new String("详情"));
    }

    @Override
    public void setTitle(String title){
        this.title.setText(note.getTitle());
    }

    @Override
    public void setDetails(Note notedetail){
      List<SpannableString> stringList=DivideNote.getDivideNote().transNoteToString(notedetail);
        for(SpannableString string:stringList){
            this.details.append(string);
        }
    }

    @Override
    public void setDate(String date){
        this.date.setText(date);
    }

}

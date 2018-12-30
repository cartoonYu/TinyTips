package com.cartoon.tinytips.Personal.MyNote;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.table.Note;
import com.cartoon.tinytips.util.Adapters.Personal.MyNote.NoteAdapter;
import com.cartoon.tinytips.util.FragmentConstant;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.UI.RevampStatusBar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyNote extends BaseActivity<MyNotePresenter> implements IMyNote.View{

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.toolbarBack)
    TextView back;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.personal_mynote_note)
    RecyclerView note;

    private NoteAdapter adapter;

    @Override
    protected MyNotePresenter initPresent(){
        return new MyNotePresenter(this);
    }

    @Override
    protected int getLayout(){
        return R.layout.personal_mynote;
    }

    @Override
    protected void initView(){
        revampStatusBar();
    }

    @Override
    protected void onPrepare(){
        presenter.initData();
    }

    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }

    @Override
    public void initNote(List<Note> notes){
        adapter=new NoteAdapter(notes);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LinearLayoutManager manager=new LinearLayoutManager(MyNote.this);
                note.setLayoutManager(manager);
                note.setAdapter(adapter);
            }
        });

    }

    @OnClick(R.id.toolbarBack)
    public void onClickBack(){
        IntentActivity.intentWithData(this,Main.class,new String("main"),FragmentConstant.getConstant().getPersonal());
        IntentActivity.finishActivity(this);
    }

    @Override
    public void onBackPressed(){
        IntentActivity.intentWithData(this,Main.class,new String("main"),FragmentConstant.getConstant().getPersonal());
        IntentActivity.finishActivity(this);
    }

}

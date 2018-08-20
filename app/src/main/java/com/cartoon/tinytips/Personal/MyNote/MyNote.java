package com.cartoon.tinytips.Personal.MyNote;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.Adapters.Personal.MyNote.Note;
import com.cartoon.tinytips.util.Adapters.Personal.MyNote.NoteAdapter;
import com.cartoon.tinytips.util.FragmentConstant;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.UI.RevampStatusBar;

import java.util.ArrayList;
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

    private List<Note> notes;
    private NoteAdapter adapter;
    @BindView(R.id.personal_mynote_note)
    RecyclerView note;


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
        initNote();
    }

    @Override
    protected void onPrepare(){

    }

    @Override
    public void onBackPressed(){
        IntentActivity.intentWithData(this,Main.class,new String("main"),FragmentConstant.personal);
        IntentActivity.finishActivity(this);
    }

    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }


    @Override
    public void initNote(){
        notes=new ArrayList<>();
        Note n=new Note("asd","java","python","C","2018-8-14");
        for(int i=0;i<20;i++){
            notes.add(n);
        }
        LinearLayoutManager manager=new LinearLayoutManager(this);
        note.setLayoutManager(manager);
        adapter=new NoteAdapter(notes);
        note.setAdapter(adapter);
    }

    @OnClick(R.id.toolbarBack)
    public void onClickBack(){
        IntentActivity.intentWithData(this,Main.class,new String("main"),FragmentConstant.personal);
        IntentActivity.finishActivity(this);
    }

}

package com.cartoon.tinytips.Personal.MyNote;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Note;
import com.cartoon.tinytips.util.ShowToast;

import java.util.List;

class MyNotePresenter extends BaseActivityPresenter<MyNote> implements IMyNote.Presenter{

    private IMyNote.View view;

    private IMyNote.Model model;

    public MyNotePresenter(IMyNote.View view){
        this.view=view;
        this.model = new MyNoteModel();
    }

    @Override
    protected void deleteView(){
        view=null;
        model=null;
    }

    @Override
    public void initData(){
        model.getNote(new ValueCallBack<List<Note>>() {
            @Override
            public void onSuccess(List<Note> notes) {
                view.initNote(notes);
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });
    }
}

package com.cartoon.tinytips.Note.Addnote;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.util.ShowToast;

public class AddNotePresenter extends BaseActivityPresenter<AddNote> implements IAddNote.Presenter {

    private IAddNote.View view;

    private IAddNote.Model model;


    @Override
    public void addNote(Note note){
        model.addNote(note, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                ShowToast.shortToast(s);
                view.intentToMain();
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });
    }

    public AddNotePresenter(IAddNote.View view){
        this.view=view;
        this.model = new AddNoteModel();
    }
    @Override
    protected void deleteView(){
        view=null;
        model = null;
    }


}

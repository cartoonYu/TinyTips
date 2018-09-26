package com.cartoon.tinytips.Note.Addnote;

import com.cartoon.tinytips.BaseActivityPresenter;

public class AddNotePresenter extends BaseActivityPresenter<AddNote> implements IAddNote.Presenter {
    private IAddNote.View view;
    public AddNotePresenter(IAddNote.View view){
        this.view=view;
    }
    @Override
    protected void deleteView(){
        view=null;
    }
}

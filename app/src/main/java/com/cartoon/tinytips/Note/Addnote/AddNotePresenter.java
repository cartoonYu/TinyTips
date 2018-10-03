package com.cartoon.tinytips.Note.Addnote;

import com.cartoon.tinytips.BaseActivityPresenter;

public class AddNotePresenter extends BaseActivityPresenter<AddNote> implements IAddNote.Presenter {
    private IAddNote.View view;

    private IAddNote.Model model;

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

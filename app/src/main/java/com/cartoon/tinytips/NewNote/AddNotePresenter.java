package com.cartoon.tinytips.NewNote;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.BaseFragmentPresenter;

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

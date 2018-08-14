package com.cartoon.tinytips.Personal.MyNote;

import com.cartoon.tinytips.BaseActivityPresenter;

class MyNotePresenter extends BaseActivityPresenter<MyNote> implements IMyNote.Presenter{

    private IMyNote.View view;

    public MyNotePresenter(IMyNote.View view){
        this.view=view;
    }

    @Override
    protected void deleteView(){
        view=null;
    }
}

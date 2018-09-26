package com.cartoon.tinytips.Note.ShareNote;

import com.cartoon.tinytips.BaseActivityPresenter;

public class ShareNotePresenter extends BaseActivityPresenter<ShareNote> implements IShareNote.Presenter {
    private IShareNote.View view;

    public ShareNotePresenter(IShareNote.View view){
        this.view=view;
    }

    @Override
    protected void deleteView(){
        view=null;
    }
}

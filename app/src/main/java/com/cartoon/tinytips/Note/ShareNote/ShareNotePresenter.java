package com.cartoon.tinytips.Note.ShareNote;

import com.cartoon.tinytips.BaseActivityPresenter;

public class ShareNotePresenter extends BaseActivityPresenter<ShareNote> implements IShareNote.Presenter {
    private IShareNote.View view;
    private IShareNote.Model model;

    public ShareNotePresenter(IShareNote.View view){
        this.view=view;
        this.model = new ShareNoteModel();
    }

    @Override
    protected void deleteView(){
        view=null;
        model = null;
    }
}

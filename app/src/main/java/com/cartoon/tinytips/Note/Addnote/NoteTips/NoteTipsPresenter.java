package com.cartoon.tinytips.Note.Addnote.NoteTips;

import com.cartoon.tinytips.BaseActivityPresenter;

public class NoteTipsPresenter extends BaseActivityPresenter<NoteTips> implements INoteTips.Presenter {
    private INoteTips.View view;
    public NoteTipsPresenter(INoteTips.View view){
        this.view=view;
    }
    @Override
    protected void deleteView(){
        view=null;
    }
}

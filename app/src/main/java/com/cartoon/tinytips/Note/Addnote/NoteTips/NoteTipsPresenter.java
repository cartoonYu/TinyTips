package com.cartoon.tinytips.Note.Addnote.NoteTips;

import com.cartoon.tinytips.BaseActivityPresenter;

public class NoteTipsPresenter extends BaseActivityPresenter<NoteTips> implements INoteTips.Presenter {
    private INoteTips.View view;
    private INoteTips.Model model;

    public NoteTipsPresenter(INoteTips.View view){
        this.view=view;
        this.model = new NoteTipsModel();
    }
    @Override
    protected void deleteView(){
        view=null;
        model = null;
    }
}

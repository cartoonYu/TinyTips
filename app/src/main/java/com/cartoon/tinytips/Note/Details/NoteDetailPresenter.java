package com.cartoon.tinytips.Note.Details;

import com.cartoon.tinytips.BaseActivityPresenter;

class NoteDetailPresenter extends BaseActivityPresenter<NoteDetail> implements INoteDetail.Presenter{

    private INoteDetail.View view;

    public NoteDetailPresenter(INoteDetail.View view){
        this.view=view;
    }

    @Override
    protected void deleteView(){
        view=null;
    }

}

package com.cartoon.tinytips.Note.Details;

import com.cartoon.tinytips.BaseActivityPresenter;

class NoteDetailPresenter extends BaseActivityPresenter<NoteDetail> implements INoteDetail.Presenter{

    private INoteDetail.View view;

    private INoteDetail.Model model;


    public NoteDetailPresenter(INoteDetail.View view){
        this.view=view;
        this.model = new NoteDetailModel();
    }

    @Override
    protected void deleteView(){
        view=null;
        model = null;
    }

}

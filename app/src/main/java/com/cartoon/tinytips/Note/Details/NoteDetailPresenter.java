package com.cartoon.tinytips.Note.Details;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Default_many;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.util.ShowToast;

class NoteDetailPresenter extends BaseActivityPresenter<NoteDetail> implements INoteDetail.Presenter{

    private INoteDetail.View view;

    private INoteDetail.Model model;

    private Default_many Dnote;

    public NoteDetailPresenter(INoteDetail.View view){
        this.view=view;
        this.model = new NoteDetailModel();
    }

    @Override
    protected void deleteView(){
        view=null;
        model = null;
    }

    @Override
    public void handleData() {
        model.getNote( new ValueCallBack<Note>(){
            @Override
            public void onSuccess(Note note){
                view.setDetails(note);
                view.setTitle(note.getTitle());
                view.setDate(note.getDate());
            }
            @Override
            public void onFail(String msg) {
                Dnote = new Default_many();
                ShowToast.shortToast(msg);
                view.setDetails(Dnote.getNote());
                view.setTitle(Dnote.getNote().getTitle());
                view.setDate(Dnote.getNote().getDate());
            }
        });
    }
}

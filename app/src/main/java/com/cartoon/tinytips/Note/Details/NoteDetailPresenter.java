package com.cartoon.tinytips.Note.Details;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.util.ShowToast;

import java.util.Map;

class NoteDetailPresenter extends BaseActivityPresenter<NoteDetail> implements INoteDetail.Presenter{

    private INoteDetail.View view;

    private INoteDetail.Model model;

    @Override
    public void initSocial(Note note) {
        model.getSocial(note, new ValueCallBack<Map<String, Integer>>() {
            @Override
            public void onSuccess(Map<String, Integer> numOfSocial) {
                view.setLike(numOfSocial.get("Like"));
                view.setCollect(numOfSocial.get("Collect"));
                view.setComment(numOfSocial.get("Comment"));
                view.setForward(numOfSocial.get("Forward"));
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        }, new ValueCallBack<Map<String, Boolean>>() {
            @Override
            public void onSuccess(Map<String, Boolean> isClick) {
                view.setLike(isClick.get("Like"));
                view.setComment(isClick.get("Comment"));
                view.setForward(isClick.get("Forward"));
                view.setCollect(isClick.get("Collect"));
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });
    }

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

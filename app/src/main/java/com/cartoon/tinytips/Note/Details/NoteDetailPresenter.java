package com.cartoon.tinytips.Note.Details;

import android.util.Log;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Default_many;
import com.cartoon.tinytips.bean.table.Note;
import com.cartoon.tinytips.bean.view.StatSocial;
import com.cartoon.tinytips.util.JudgeEmpty;
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
    public void handleStatSocial(final StatSocial social) {
        if(JudgeEmpty.isEmpty(social)){
            Dnote = new Default_many();
            view.setDetails(Dnote.getNote());
            view.setTitle(Dnote.getNote().getTitle());
            view.setDate(Dnote.getNote().getDate());
            return;
        }
        model.getNote(social, new ValueCallBack<Note>() {
            @Override
            public void onSuccess(Note note) {
                revampUI(note,social);
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });
    }

    @Override
    public void handleNote(final Note note) {
        if(JudgeEmpty.isEmpty(note)){
            Dnote = new Default_many();
            view.setDetails(Dnote.getNote());
            view.setTitle(Dnote.getNote().getTitle());
            view.setDate(Dnote.getNote().getDate());
            return;
        }
        model.getSocial(note, new ValueCallBack<StatSocial>() {
            @Override
            public void onSuccess(StatSocial social) {
                revampUI(note,social);
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });
    }

    /**
     * 更新前端数据
     * @param note
     * @param social
     */
    private void revampUI(Note note,StatSocial social){
        view.setDetails(note);
        view.setTitle(note.getTitle());
        view.setDate(note.getDate());
        view.setNote(note);
        view.setCollect(social.getNumOfCollect(),social.isCollect());
        view.setComment(social.getNumOfComment(),social.isComment());
        view.setLove(social.getNumOfLove(),social.isLove());
        view.setForward(social.getNumOfForward(),social.isForward());
    }
}

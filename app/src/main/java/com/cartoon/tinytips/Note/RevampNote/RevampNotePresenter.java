package com.cartoon.tinytips.Note.RevampNote;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Note;
import com.cartoon.tinytips.util.Note.DivideNote;
import com.cartoon.tinytips.util.ShowToast;

class RevampNotePresenter extends BaseActivityPresenter<RevampNote> implements IRevampNote.Presenter {

    private IRevampNote.View view;

    private IRevampNote.Model model;

    private DivideNote divideNote;

    @Override
    public void initData(Note note) {
        view.setTitle(note.getTitle());
        view.setContent(divideNote.transNoteToString(note));
    }

    @Override
    public void revampNote(Note oldNote) {
        Note note=divideNote.transStringToNote(view.getContent());
        note.setTitle(view.getNoteTitle());
        model.revampNote(oldNote,note, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                ShowToast.shortToast(s);
                view.finishActivity();
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });
    }

    public RevampNotePresenter(IRevampNote.View view){
        this.view=view;
        model=new RevampNoteModel();
        divideNote=DivideNote.getDivideNote();
    }

    @Override
    protected void deleteView() {
    }
}

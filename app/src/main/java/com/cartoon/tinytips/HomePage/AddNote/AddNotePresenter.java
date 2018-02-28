package com.cartoon.tinytips.HomePage.AddNote;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.data.TableNote.Note;

import java.util.Date;

/**
 * Created by cartoon on 2018/2/17.
 */

class AddNotePresenter extends BaseActivityPresenter<AddNote> implements IAddNote.Presenter{
    private IAddNote.View view;
    private IAddNote.Model model;
    public AddNotePresenter(IAddNote.View view){
        this.view=view;
        this.model=new AddNoteModel(view.getMyDatabaseHelper().getWritableDatabase());
    }
    @Override
    public void saveNote(String title, String date, String author,String imageDetails, String details, String[] classify, boolean isCollect) {
        model.setNote(title, date, author,imageDetails, details,  classify, isCollect);
    }

    @Override
    public void addNote() {
        saveNote("title",view.getDate(),"author",view.getImageDetails(),view.getDetails(),new String[]{"classify"},true);
        model.addNote(new ValueCallBack<String>() {
            @Override
            public void onSuccess(String Code) {
                view.showToast(Code);
            }

            @Override
            public void onFail(String Code) {
                view.showToast(Code);
            }
        });
    }
}

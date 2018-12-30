package com.cartoon.tinytips.Discover.NoteList;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.bean.table.Note;
import com.cartoon.tinytips.util.ShowToast;

public class NoteListPresenter extends BaseActivityPresenter<NoteList> implements INoteList.Presenter {
    private INoteList.View view;
    private INoteList.Model model;
    private String contents;
    public NoteListPresenter(INoteList.View view){
        this.view=view;
        this.model = new NoteListModel();
    }

    @Override
    protected void deleteView() {
        view=null;
        model = null;
    }

    @Override
    public void handleData(){
        model.getUserInformation(new ValueCallBack<Information>() {
            @Override
            public void onSuccess(Information Information) {
                view.setHeadPro(Information.getHeadPortrait());
                view.setUserName(Information.getNickName());
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });

        model.getNote(new ValueCallBack<Note>() {
            @Override
            public void onSuccess(Note note) {
                view.setTitle(note.getTitle());
                view.setContents(note.getWordDetails().get(0));
                view.setNumOC("2");
                view.setNumOR("2");
                view.setNumOF("2");
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });
    }
}

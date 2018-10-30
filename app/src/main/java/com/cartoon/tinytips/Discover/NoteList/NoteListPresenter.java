package com.cartoon.tinytips.Discover.NoteList;

import com.cartoon.tinytips.BaseActivityPresenter;

public class NoteListPresenter extends BaseActivityPresenter<NoteList> implements INoteList.Presenter {
    private INoteList.View view;
    private INoteList.Model model;

    public NoteListPresenter(INoteList.View view){
        this.view=view;
        this.model = new NoteListModel();
    }

    @Override
    protected void deleteView() {
        view=null;
        model = null;
    }
}

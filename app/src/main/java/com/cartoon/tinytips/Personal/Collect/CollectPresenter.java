package com.cartoon.tinytips.Personal.Collect;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.data.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cartoon on 2018/3/5.
 */

class CollectPresenter extends BaseActivityPresenter<Collect>
        implements ICollect.Presenter{

    private ICollect.View view;
    private ICollect.Model model;
    private List<Note> noteList;

    public CollectPresenter(ICollect.View view){
        this.view=view;
        this.model=new CollectModel();
        this.noteList=new ArrayList<>();
    }
    @Override
    public void initData(){
        model.getNoteList(new ValueCallBack<List<Note>>() {
            @Override
            public void onSuccess(List<Note> list) {
                noteList.addAll(list);
                view.refreshAdapter();
            }
            @Override
            public void onFail(String code) {
                view.showToast(code);
            }
        });
    }
    @Override
    public List<Note> getNoteList(){
        return noteList;
    }
    @Override
    public void deleteView(){
        view=null;
        model=null;
    }
}

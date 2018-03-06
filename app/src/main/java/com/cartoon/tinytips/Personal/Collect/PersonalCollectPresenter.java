package com.cartoon.tinytips.Personal.Collect;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.data.TableNote.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cartoon on 2018/3/5.
 */

class PersonalCollectPresenter extends BaseActivityPresenter<PersonalCollect>
        implements IPersonalCollect.Presenter{

    private IPersonalCollect.View view;
    private IPersonalCollect.Model model;
    private List<Note> noteList;

    public PersonalCollectPresenter(IPersonalCollect.View view){
        this.view=view;
        this.model=new PersonalCollectModel();
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
}

package com.cartoon.tinytips.Personal.PersonalHomepage;

import android.util.Log;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.util.ShowToast;

import java.util.List;

class PersonalHomepagePresenter extends BaseActivityPresenter<PersonalHomepage> implements IPersonalHomepage.Presenter{
    private IPersonalHomepage.View view;
    private IPersonalHomepage.Model model;
    private Information information;
    private List<Note> noteList;

    public PersonalHomepagePresenter(IPersonalHomepage.View view){
        this.view=view;
        this.model = new PersonalHomepageModel();
    }
    @Override
    public void deleteView(){
        view=null;
        model = null;
    }

    @Override
    public void initData(){
        model.getInformation(new ValueCallBack<Information>() {
            @Override
            public void onSuccess(Information personalInformation) {
                view.setHeadPro(personalInformation.getHeadPortrait());
                view.setNickName(personalInformation.getNickName());
                view.setMajor(personalInformation.getMajor());
                view.setSchool(personalInformation.getSchool());
                view.setInterest(personalInformation.getInterest());

            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });
    }

    public List<Note> getNoteList(){
        model.getNoteList(new ValueCallBack<List<Note>>() {
            @Override
            public void onSuccess(List<Note> notes) {
                noteList = notes;
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });

        return noteList;
    }

}

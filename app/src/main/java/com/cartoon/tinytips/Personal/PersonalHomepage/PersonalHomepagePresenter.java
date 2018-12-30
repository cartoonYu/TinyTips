package com.cartoon.tinytips.Personal.PersonalHomepage;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.bean.table.Note;
import com.cartoon.tinytips.bean.view.StatSocial;
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
    public void initInformation(Information information){
        model.getInformation(information,new ValueCallBack<Information>() {
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

    @Override
    public void initDynamicState() {
        ShowToast.longToast("数据加载中");
        model.getDynamicStateList(new ValueCallBack<List<StatSocial>>() {
            @Override
            public void onSuccess(List<StatSocial> list) {
                view.initDynamicState(list);
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });
    }

}

package com.cartoon.tinytips.Personal;

import com.cartoon.tinytips.BaseFragmentPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.util.ShowToast;

class PersonalPresenter extends BaseFragmentPresenter<Personal> implements IPersonal.Presenter {

    private IPersonal.View view;

    private IPersonal.Model model;

    Information information;

    public PersonalPresenter(IPersonal.View view){
        this.view=view;
        this.model=new PersonalModel();
    }

    @Override
    public void initData(){
        saveInformation();
        model.setInformation(information);
        model.getPersonalInformation(new ValueCallBack<Information>() {
            @Override
            public void onSuccess(Information personalInformation) {
                view.setHeadPro(personalInformation.getHeadPortrait());
                view.setNotes(personalInformation.getNumOfNote());
                getNoteNum();
                view.setFans("粉丝  5");
                view.setAttentions("关注  20");
                view.setNickName(personalInformation.getNickName());
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });
    }

    public void saveInformation(){
       information = view.getInformation();
    }

    @Override
    protected void deleteView(){
        view=null;
        model=null;
    }

    /**
     * 功能
     * 统计该用户的笔记数量
     */
    private void getNoteNum(){
        model.getNoteNum(information, new ValueCallBack<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                view.setNotes("笔记  "+integer);
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });
    }
}

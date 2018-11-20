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
        model.getPersonalInformation(new ValueCallBack<Information>() {
            @Override
            public void onSuccess(Information Information) {
                view.setHeadPro(Information.getHeadPortrait());
                getNoteNum();
                view.setFans(5);
                view.setAttentions(20);
                view.setNickName(Information.getNickName());
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });
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
        model.getNoteNum(new ValueCallBack<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                view.setNotes(integer);
            }

            @Override
            public void onFail(String msg) {
                view.setNotes(0);
                ShowToast.shortToast(msg);
            }
        });
    }
}

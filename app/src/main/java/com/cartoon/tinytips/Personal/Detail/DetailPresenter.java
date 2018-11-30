package com.cartoon.tinytips.Personal.Detail;

import android.util.Log;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Local.LocalInformation;
import com.cartoon.tinytips.util.ShowToast;

class DetailPresenter extends BaseActivityPresenter<Detail> implements IDetail.Presenter{

    private IDetail.View view;

    private IDetail.Model model;

    public DetailPresenter(IDetail.View view){
        this.view=view;
        this.model = new DetailModel();
    }

    @Override
    public void initData() {
        model.getInformation(new ValueCallBack<Information>() {
            @Override
            public void onSuccess(Information personalInformation) {
                view.setHeadPro(personalInformation.getHeadPortrait());
                view.setNickName(personalInformation.getNickName());
                view.setDegree(personalInformation.getBackground());
                view.setMajor(personalInformation.getMajor());
                view.setResume(personalInformation.getResume());
                view.setSchool(personalInformation.getSchool());
                view.setRegisterData(personalInformation.getDate());
                view.setInterest(personalInformation.getInterest());
            }


            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });
    }

    @Override
    protected void deleteView(){
        view = null;
        model = null;
    }

}

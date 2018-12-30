package com.cartoon.tinytips.Personal.Detail;

import android.net.Uri;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.util.ShowToast;
import com.cartoon.tinytips.util.file.UriAndFile;

import java.io.File;

class DetailPresenter extends BaseActivityPresenter<Detail> implements IDetail.Presenter{

    private IDetail.View view;

    private IDetail.Model model;

    private Information revampInformation;

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
    public void revampHeadPro(Uri source) {
        revampInformation=new Information();
        File headPro=UriAndFile.getInstance().uriToFile(source);
        revampInformation.setHeadPortrait(headPro);
        revampInformation(revampInformation);
    }

    @Override
    public void resume(String resume) {
        revampInformation=new Information();
        revampInformation.setResume(resume);
        revampInformation(revampInformation);
    }

    private void revampInformation(Information information) {
        model.revampInformation(information, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                ShowToast.shortToast(s);
                initData();
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

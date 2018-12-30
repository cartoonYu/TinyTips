package com.cartoon.tinytips.Personal.Detail.Revamp;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.util.ShowToast;

public class RevampInformationPresenter extends BaseActivityPresenter<RevampInformation> implements IRevampInformation.Presenter {

    private IRevampInformation.View view;

    private IRevampInformation.Model model;

    @Override
    public void initUI(String type) {
        StringBuilder builder=new StringBuilder("请输入");
        switch (type){
            case "resume":{
                builder.append("简介");
                break;
            }
            case "school":{
                builder.append("学校");
                break;
            }
            case "major":{
                builder.append("专业");
                break;
            }
            case "degree": {
                builder.append("学历");
                break;
            }
        }
        view.setToolBarText(builder.toString());
    }

    @Override
    public void revampInformation(String type) {
        String revamp=view.getInput();
        if(revamp.equals("")){
            ShowToast.shortToast("未输入任意信息");
            return;
        }
        Information information=new Information();
        switch (type){
            case "resume":{
                information.setResume(revamp);
                break;
            }
            case "school":{
                information.setSchool(revamp);
                break;
            }
            case "major":{
                information.setMajor(revamp);
                break;
            }
            case "degree": {
                information.setBackground(revamp);
                break;
            }
        }
        model.revampInformation(information, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                ShowToast.shortToast(s);
                view.intentToDetails();
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });
    }

    public RevampInformationPresenter(IRevampInformation.View view){
        this.view=view;
        this.model=new RevampInformationModel();
    }

    @Override
    protected void deleteView() {
        this.view=null;
        this.model=null;
    }
}

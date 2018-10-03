package com.cartoon.tinytips.Personal.Setting;

import com.cartoon.tinytips.BaseActivityPresenter;

class SettingPresenter extends BaseActivityPresenter<Setting> implements ISetting.Presenter{

    private ISetting.View view;
    private ISetting.Model model;

    public SettingPresenter(ISetting.View view){
        this.view=view;
        this.model = new SettingModel();
    }

    @Override
    protected void deleteView(){
        view=null;
        model = null;
    }

}

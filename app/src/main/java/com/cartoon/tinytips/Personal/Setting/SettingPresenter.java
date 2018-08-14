package com.cartoon.tinytips.Personal.Setting;

import com.cartoon.tinytips.BaseActivityPresenter;

class SettingPresenter extends BaseActivityPresenter<Setting> implements ISetting.Presenter{

    private ISetting.View view;

    public SettingPresenter(ISetting.View view){
        this.view=view;
    }

    @Override
    protected void deleteView(){
        view=null;
    }

}

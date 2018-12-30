package com.cartoon.tinytips.Start;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.util.ShowToast;

public class StartActivityPresenter extends BaseActivityPresenter<StartActivity> implements IStartActivity.Presenter {

    private IStartActivity.View view;

    protected IStartActivity.Model model;

    private Information info;

    @Override
    protected void deleteView() {
    }

    public StartActivityPresenter(IStartActivity.View view){
        this.view=view;
        this.model=new StartActivityModel();
    }

    @Override
    public void getInformation(){   //从数据库获取个人信息

        model.getPersonalInformation(new ValueCallBack<Information>() {
            @Override
            public void onSuccess(Information information) {
                info = information;
                view.intentToMain(information);
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
                view.intentToLogin();
            }
        });

    }

}

package com.cartoon.tinytips.Main;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.bean.table.Information;

public class MainPresenter extends BaseActivityPresenter<Main> implements IMain.Presenter{
    protected IMain.View view;

    protected IMain.Model model;

    private Information info;

    public MainPresenter(IMain.View view){
        this.view=view;
        this.model = new MainModel();
    }
    @Override
    protected void deleteView(){
        view=null;
        model = null;
    }

    public Information getInformation(){   //从数据库获取个人信息

       /* model.getPersonalInformation(new ValueCallBack<Information>() {
            @Override
            public void onSuccess(Information information) {
                info = information;
                ShowToast.shortToast("成功啦");
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });*/

        return info;

    }
    public Information getInfo(){             //返回个人信息给页面
        return info;
    }

}

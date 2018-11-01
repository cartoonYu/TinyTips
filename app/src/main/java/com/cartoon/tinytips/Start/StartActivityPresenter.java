package com.cartoon.tinytips.Start;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.Main.IMain;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
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

    public void getInformation(){   //从数据库获取个人信息

        model.getPersonalInformation(new ValueCallBack<Information>() {
            @Override
            public void onSuccess(Information information) {
                info = information;
                //ShowToast.shortToast("成功啦");
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
                info = null;
            }

        });



    }

    public Information getInfo(){             //返回个人信息给页面
        return info;
    }
}

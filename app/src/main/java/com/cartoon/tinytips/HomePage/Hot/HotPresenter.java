package com.cartoon.tinytips.HomePage.Hot;

import com.cartoon.tinytips.BaseFragmentPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.view.StatSocial;
import com.cartoon.tinytips.util.ShowToast;

import java.util.List;

class HotPresenter extends BaseFragmentPresenter<Hot> implements IHot.Presenter{

    private IHot.View view;

    private IHot.Model model;

    @Override
    public void initData(){
        model.initData(new ValueCallBack<List<StatSocial>>() {
            @Override
            public void onSuccess(List<StatSocial> list) {
                view.initData(list);
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });
    }

    public HotPresenter(IHot.View view){
        this.view=view;
        this.model = new HotModel();
    }

    @Override
    protected void deleteView(){
        view=null;
        model = null;
    }
}

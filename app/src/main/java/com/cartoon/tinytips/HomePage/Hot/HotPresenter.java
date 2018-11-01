package com.cartoon.tinytips.HomePage.Hot;

import com.cartoon.tinytips.BaseFragmentPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.util.ShowToast;

import java.util.List;

class HotPresenter extends BaseFragmentPresenter<Hot> implements IHot.Presenter{

    private IHot.View view;

    private IHot.Model model;

    @Override
    public void initData(){
        model.initData(new ValueCallBack<List<HotItem>>() {
            @Override
            public void onSuccess(List<HotItem> hotItems) {
                view.initData(hotItems);
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

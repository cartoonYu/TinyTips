package com.cartoon.tinytips.Personal.Collect;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.util.ShowToast;

import java.util.List;

class CollectPresenter extends BaseActivityPresenter<Collect> implements ICollect.Presenter{

    private ICollect.View view;
    private ICollect.Model model;

    @Override
    public void initData() {
        model.initData(new ValueCallBack<List<com.cartoon.tinytips.util.Adapters.Personal.Collect.Collect>>() {
            @Override
            public void onSuccess(List<com.cartoon.tinytips.util.Adapters.Personal.Collect.Collect> collects) {
                view.initCollect(collects);
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });
    }

    public CollectPresenter(ICollect.View view){
        this.view=view;
        this.model = new CollectModel();
    }

    @Override
    protected void deleteView(){
        view=null;
        model = null;
    }
}

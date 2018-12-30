package com.cartoon.tinytips.Personal.Collect;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.view.StatSocial;
import com.cartoon.tinytips.util.ShowToast;

import java.util.List;

class CollectPresenter extends BaseActivityPresenter<Collect> implements ICollect.Presenter{

    private ICollect.View view;
    private ICollect.Model model;

    @Override
    public void initData() {
        ShowToast.longToast("数据加载中");
        model.initData(new ValueCallBack<List<StatSocial>>() {
            @Override
            public void onSuccess(List<StatSocial> socials) {
                view.initCollect(socials);
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

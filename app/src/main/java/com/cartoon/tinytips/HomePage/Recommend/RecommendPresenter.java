package com.cartoon.tinytips.HomePage.Recommend;

import com.cartoon.tinytips.BaseFragmentPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.view.StatSocial;
import com.cartoon.tinytips.util.ShowToast;

import java.util.List;

class RecommendPresenter extends BaseFragmentPresenter<Recommend> implements IRecommend.Presenter{

    private IRecommend.View view;

    private IRecommend.Model model;

    @Override
    public void initData(){
        model.initData(new ValueCallBack<List<StatSocial>>() {
            @Override
            public void onSuccess(List<StatSocial> statSocials) {
                view.initData(statSocials);
            }

            @Override
            public void onFail(String msg) {
                ShowToast.shortToast(msg);
            }
        });
    }

    public RecommendPresenter(IRecommend.View view){
        this.view=view;
        this.model = new RecommendModel();
    }

    @Override
    protected void deleteView(){
        view=null;
        model = null;
    }

}

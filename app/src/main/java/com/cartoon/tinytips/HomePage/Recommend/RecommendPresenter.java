package com.cartoon.tinytips.HomePage.Recommend;

import com.cartoon.tinytips.BaseFragmentPresenter;

class RecommendPresenter extends BaseFragmentPresenter<Recommend> implements IRecommend.Presenter{

    private IRecommend.View view;

    private IRecommend.Model model;

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

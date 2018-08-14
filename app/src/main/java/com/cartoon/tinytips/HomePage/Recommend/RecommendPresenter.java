package com.cartoon.tinytips.HomePage.Recommend;

import com.cartoon.tinytips.BaseFragmentPresenter;

class RecommendPresenter extends BaseFragmentPresenter<Recommend> implements IRecommend.Presenter{

    private IRecommend.View view;

    public RecommendPresenter(IRecommend.View view){
        this.view=view;
    }

    @Override
    protected void deleteView(){
        view=null;
    }

}

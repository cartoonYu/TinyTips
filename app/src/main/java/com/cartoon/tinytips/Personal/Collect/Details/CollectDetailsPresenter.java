package com.cartoon.tinytips.Personal.Collect.Details;

import com.cartoon.tinytips.BaseActivityPresenter;

/**
 * Created by cartoon on 2018/3/6.
 */

class CollectDetailsPresenter extends BaseActivityPresenter<CollectDetails> implements ICollectDetails.Presenter{

    private ICollectDetails.View view;
    private ICollectDetails.Model model;

    public CollectDetailsPresenter(ICollectDetails.View view){
        this.view=view;
        this.model=new CollectDetailsModel();
    }
    @Override
    public void deleteView(){
        view=null;
        model=null;
    }
}

package com.cartoon.tinytips.Personal.Collect;

import com.cartoon.tinytips.BaseActivityPresenter;

class CollectPresenter extends BaseActivityPresenter<Collect> implements ICollect.Presenter{
    private ICollect.View view;

    public CollectPresenter(ICollect.View view){
        this.view=view;
    }

    @Override
    protected void deleteView(){
        view=null;
    }
}

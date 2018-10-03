package com.cartoon.tinytips.Personal.Collect;

import com.cartoon.tinytips.BaseActivityPresenter;

class CollectPresenter extends BaseActivityPresenter<Collect> implements ICollect.Presenter{
    private ICollect.View view;
    private ICollect.Model model;

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

package com.cartoon.tinytips.Note.Addnote.Athority;

import com.cartoon.tinytips.BaseActivityPresenter;

public class AthorityPresenter extends BaseActivityPresenter<Athority> implements IAthority.Presenter {
    private IAthority.View view;
    public AthorityPresenter(IAthority.View view){
        this.view=view;
    }
    @Override
    protected void deleteView(){
        view=null;
    }
}

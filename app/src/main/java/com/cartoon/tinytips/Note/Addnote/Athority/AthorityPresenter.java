package com.cartoon.tinytips.Note.Addnote.Athority;

import com.cartoon.tinytips.BaseActivityPresenter;

public class AthorityPresenter extends BaseActivityPresenter<Athority> implements IAthority.Presenter {
    private IAthority.View view;
    private IAthority.Model model;

    public AthorityPresenter(IAthority.View view){
        this.view=view;
        this.model = new AthorityModel();
    }
    @Override
    protected void deleteView(){
        view=null;
        model = null;
    }
}

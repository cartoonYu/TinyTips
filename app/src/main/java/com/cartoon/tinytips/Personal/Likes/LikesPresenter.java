package com.cartoon.tinytips.Personal.Likes;

import com.cartoon.tinytips.BaseActivityPresenter;

public class LikesPresenter extends BaseActivityPresenter<Likes> implements ILikes.Presenter {
    private ILikes.View view;

    private ILikes.Model model;

    public LikesPresenter(ILikes.View view){
        this.view=view;
        this.model = new LikesModel();
    }

    @Override
    protected void deleteView() {

    }
}

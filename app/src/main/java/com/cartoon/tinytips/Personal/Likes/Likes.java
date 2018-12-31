package com.cartoon.tinytips.Personal.Likes;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.R;

public class Likes extends BaseActivity<LikesPresenter> implements ILikes.View{
    @Override
    protected LikesPresenter initPresent() {
        return new LikesPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.likes;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onPrepare() {

    }
}

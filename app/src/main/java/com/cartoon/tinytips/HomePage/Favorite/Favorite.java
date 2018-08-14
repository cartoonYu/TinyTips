package com.cartoon.tinytips.HomePage.Favorite;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.R;

public class Favorite extends BaseFragment<FavoritePresenter> implements IFavorite.View{

    @Override
    protected FavoritePresenter initPresent() {
        return new FavoritePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.home_favorite;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onPrepare() {

    }
}

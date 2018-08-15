package com.cartoon.tinytips.HomePage.Favorite;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.Adapters.Homepage.FavoriteAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Favorite extends BaseFragment<FavoritePresenter> implements IFavorite.View{
    private FavoriteItem[] recommendItems = {
            new FavoriteItem(R.drawable.apple, "asdasd","asdasd","asdasd",123,213,23,12,"12"),
            new FavoriteItem(R.drawable.apple, "asdasd","asdasd","asdasd",23,123,123,21,"12"),
            new FavoriteItem(R.drawable.apple, "asdasd","asdasd","asdasd",123,23,123,21,"12"),
            new FavoriteItem(R.drawable.apple, "asdasd","asdasd","asdasd",1223,223,123,21,"12"),
            new FavoriteItem(R.drawable.apple, "asdasd","asdasd","asdasd",124,231,4131,21,"12"),
            new FavoriteItem(R.drawable.apple, "asdasd","asdasd","asdasd",345,345,63,21,"12")
    };

    private FavoriteAdapter adapter;

    private List<FavoriteItem> FavoriteItemList;

    @BindView(R.id.home_favorite_recyclerview)
    RecyclerView favorite_recyclerView;
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
        FavoriteItemList = new ArrayList<>();
        for (FavoriteItem one : recommendItems) {
            FavoriteItemList.add(one);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        favorite_recyclerView.setLayoutManager(layoutManager);
        adapter = new FavoriteAdapter(FavoriteItemList);
        favorite_recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onPrepare() {

    }
}

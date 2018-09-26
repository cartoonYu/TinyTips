package com.cartoon.tinytips.HomePage.Favorite;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.Adapters.Homepage.FavoriteAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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

    private ClassicsHeader Classicsheader;

    @BindView(R.id.home_favorite_recyclerview)
    RecyclerView favorite_recyclerView;

    @BindView(R.id.Favoriterefresh)
    RefreshLayout Favoriterefresh;

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

        Favoriterefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        Favoriterefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000/*,false*/);//传入false表示加载失败
            }
        });

        Classicsheader = new ClassicsHeader(getContext()).setTextSizeTitle(0);
        Classicsheader.setTextSizeTime(0);
        Classicsheader.setAccentColor(Color.parseColor("#444444"));
        Classicsheader.setPrimaryColor(Color.parseColor("#f2f2f2"));
        Classicsheader.setDrawableMarginRight(-5);
        Favoriterefresh.setRefreshHeader(Classicsheader);
        Favoriterefresh.setRefreshFooter(new ClassicsFooter(getContext()));
    }

    @Override
    protected void onPrepare() {

    }
}

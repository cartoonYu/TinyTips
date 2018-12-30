package com.cartoon.tinytips.HomePage.Favorite;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.util.Adapters.Homepage.FavoriteAdapter;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Favorite extends BaseFragment<FavoritePresenter> implements IFavorite.View{

    private FavoriteAdapter adapter;

    private List<FavoriteItem> FavoriteItemList;

    private ClassicsHeader Classicsheader;

    private Information information;

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
        presenter.initData();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        favorite_recyclerView.setLayoutManager(layoutManager);
        if(JudgeEmpty.isEmpty(FavoriteItemList)){
            FavoriteItemList=new ArrayList<>();
        }
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
    public void initData(List<FavoriteItem> items){
        FavoriteItemList=new ArrayList<>();
        this.FavoriteItemList=items;
    }

    @Override
    protected void onPrepare() {

    }
}

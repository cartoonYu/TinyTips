package com.cartoon.tinytips.HomePage.Recommend;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.Adapters.Homepage.HomeRecommendAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;

public class Recommend extends BaseFragment<RecommendPresenter> implements IRecommend.View{

    private HomeRecommendAdapter adapter;

    private List<RecommendItem> recommendItemList;
    private ClassicsHeader Classicsheader;

    @BindView(R.id.home_recommend_recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.Recommendrefresh)
    RefreshLayout RecommendRefresh;


    @Override
    protected RecommendPresenter initPresent() {
        return new RecommendPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.home_recommend;
    }

    @Override
    protected void initView() {
        presenter.initData();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HomeRecommendAdapter(recommendItemList);
        recyclerView.setAdapter(adapter);

        RecommendRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        RecommendRefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
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
        RecommendRefresh.setRefreshHeader(Classicsheader);
        RecommendRefresh.setRefreshFooter(new ClassicsFooter(getContext()));
    }

    @Override
    protected void onPrepare() {

    }

    @Override
    public void initData(List<RecommendItem> recommends){
        recommendItemList=recommends;
    }

}

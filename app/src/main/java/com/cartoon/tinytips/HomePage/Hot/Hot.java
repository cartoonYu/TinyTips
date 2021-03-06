package com.cartoon.tinytips.HomePage.Hot;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.view.StatSocial;
import com.cartoon.tinytips.util.Adapters.Homepage.HotAdapter;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Hot extends BaseFragment<HotPresenter> implements IHot.View{

    private HotAdapter adapter;

    private ClassicsHeader Classicsheader;

    @BindView(R.id.home_hot_recyclerview)
    RecyclerView hot_recyclerView;

    @BindView(R.id.Hotrefresh)
    RefreshLayout Hotrefresh;

    @Override
    protected HotPresenter initPresent() {
        return new HotPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.home_hot;
    }

    @Override
    protected void initView() {
        Hotrefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        Hotrefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
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
        Hotrefresh.setRefreshHeader(Classicsheader);
        Hotrefresh.setRefreshFooter(new ClassicsFooter(getContext()));
    }

    @Override
    protected void onPrepare() {
        initData();
    }

    @Override
    public void initData() {
        presenter.initData();
    }

    @Override
    public void initData(final List<StatSocial> list){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
                hot_recyclerView.setLayoutManager(layoutManager);
                adapter = new HotAdapter(Hot.this,list);
                hot_recyclerView.setAdapter(adapter);
            }
        });
    }

}

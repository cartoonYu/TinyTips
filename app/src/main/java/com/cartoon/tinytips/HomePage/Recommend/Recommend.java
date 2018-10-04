package com.cartoon.tinytips.HomePage.Recommend;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.Note.Details.NoteDetail;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.Adapters.Homepage.HomeRecommendAdapter;
import com.cartoon.tinytips.util.IntentActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class Recommend extends BaseFragment<RecommendPresenter> implements IRecommend.View{

    private RecommendItem[] recommendItems = {
            new RecommendItem(R.drawable.apple, "asdasd","asdasd","asdasd",123,213,23),
            new RecommendItem(R.drawable.apple, "asdasd","asdasd","asdasd",23,123,123),
            new RecommendItem(R.drawable.apple, "asdasd","asdasd","asdasd",123,23,123),
            new RecommendItem(R.drawable.apple, "asdasd","asdasd","asdasd",1223,223,123),
            new RecommendItem(R.drawable.apple, "asdasd","asdasd","asdasd",124,231,4131),
            new RecommendItem(R.drawable.apple, "asdasd","asdasd","asdasd",345,345,63),
    };

    private HomeRecommendAdapter adapter;

    private List<RecommendItem> recommendItemList;
    private ClassicsHeader Classicsheader;

    @BindView(R.id.home_recommend_recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.Recommendrefresh)
    RefreshLayout Recommendrefresh;


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
        recommendItemList = new ArrayList<>();
        for (RecommendItem one : recommendItems) {
            recommendItemList.add(one);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HomeRecommendAdapter(recommendItemList);
        recyclerView.setAdapter(adapter);

        Recommendrefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        Recommendrefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
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
        Recommendrefresh.setRefreshHeader(Classicsheader);
        Recommendrefresh.setRefreshFooter(new ClassicsFooter(getContext()));
    }

    @Override
    protected void onPrepare() {

    }

}

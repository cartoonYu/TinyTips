package com.cartoon.tinytips.HomePage.Recommend;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.BaseFragmentPresenter;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.Adapters.Homepage.HomeRecommendAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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

    @BindView(R.id.home_recommend_recyclerview)
    RecyclerView recyclerView;


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

    }

    @Override
    protected void onPrepare() {

    }
}

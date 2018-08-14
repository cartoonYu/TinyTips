package com.cartoon.tinytips.HomePage.Recommend;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.BaseFragmentPresenter;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.Adapters.homepage.HomeRecommendAdapter;

import java.util.ArrayList;
import java.util.List;

public class Recommend extends BaseFragment {

        private RecommendItem[] recommendItems = {
                new RecommendItem(R.drawable.apple, "asdasd","asdasd","asdasd",123,213,23),
                new RecommendItem(R.drawable.apple, "asdasd","asdasd","asdasd",23,123,123),
                new RecommendItem(R.drawable.apple, "asdasd","asdasd","asdasd",123,23,123),
                new RecommendItem(R.drawable.apple, "asdasd","asdasd","asdasd",1223,223,123),
                new RecommendItem(R.drawable.apple, "asdasd","asdasd","asdasd",124,231,4131),
                new RecommendItem(R.drawable.apple, "asdasd","asdasd","asdasd",345,345,63),
        };

        private HomeRecommendAdapter adapter;

        private List<RecommendItem> recommendItemList = new ArrayList<>();

        private RecyclerView recyclerView;

    public Recommend() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_recommend, container, false);
        recyclerView = view.findViewById(R.id.home_recommend_recyclerview);
        return view;
    }

    @Override
    protected BaseFragmentPresenter initPresent() {
        return null;
    }

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void initView() {
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

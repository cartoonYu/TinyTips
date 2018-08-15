package com.cartoon.tinytips.HomePage.Hot;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.Adapters.Homepage.HotAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Hot extends BaseFragment<HotPresenter> implements IHot.View{
    private HotItem[] HotItems = {
            new HotItem("213","12sadadasdasdsasdasdasdasdasdasdasdassd3",321),
            new HotItem("213","12asdadasdsadsadasdassdasdasdassdassd3",321),
            new HotItem("213","12asdadadasdadasdassdadsaddasd3",321),
            new HotItem("213","12dadadasdadasdasdadadasdadasdas3",321),
            new HotItem("213","12dadadasdadasdasdadadasdadasdas3",321),
            new HotItem("213","12dadadasdadasdasdadadasdadasdasdadadasdadasdas3",321),
            new HotItem("213","12dadadasdadasdasdadadasdadasdas3",321),
            new HotItem("213","12dadadasdadasdas3",321),
            new HotItem("213","12dadadasdadasdasdadadasdadasdas3",321),
    };
    private HotAdapter adapter;

    private List<HotItem> HotItemList;

    @BindView(R.id.home_hot_recyclerview)
    RecyclerView hot_recyclerView;
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
        HotItemList= new ArrayList<>();
        for (HotItem one : HotItems) {
            HotItemList.add(one);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        hot_recyclerView.setLayoutManager(layoutManager);
        adapter = new HotAdapter(HotItemList);
        hot_recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onPrepare() {

    }
}

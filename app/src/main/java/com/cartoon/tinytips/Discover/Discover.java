package com.cartoon.tinytips.Discover;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.R;

import com.cartoon.tinytips.util.Adapters.Major;
import com.cartoon.tinytips.util.Adapters.MajorAdapter;
import com.cartoon.tinytips.util.UI.RevampStatusBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Discover extends BaseFragment<DiscoverPresenter> implements IDiscover.View{

    private Major[] major = {new Major("IT互联网", R.drawable.apple), new Major("经济/管理", R.drawable.apple),
            new Major("电子/通信", R.drawable.apple), new Major("政治/法律", R.drawable.apple),
            new Major("轨道/交通", R.drawable.apple), new Major("艺术/设计", R.drawable.apple),
            new Major("机械/自动化", R.drawable.apple), new Major("汉语言/文学", R.drawable.apple),
            new Major("建筑学", R.drawable.apple), new Major("外语", R.drawable.apple),
            new Major("物理/材料", R.drawable.apple), new Major("化学/环境", R.drawable.apple),
            new Major("数学", R.drawable.apple), new Major("纺织/服装", R.drawable.apple)};

    private List<Major> MJList = new ArrayList<>();

    private MajorAdapter adapter;

    @BindView(R.id.major_recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.search)
    View search;

    @Override
    protected DiscoverPresenter initPresent(){
        return new DiscoverPresenter(this);
    }

    @Override
    protected int getLayout(){
        return R.layout.discover;
    }

    @Override
    protected void initView(){
        revampStatusBar();
        removeUnderLine();
        recyclerList();
    }

    @Override
    protected void onPrepare(){

    }

    @Override
    public void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }

    @Override
    public void removeUnderLine() {
        if (search != null) {
            search.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void recyclerList() {
        for (Major one : major ){
            MJList.add(one);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MajorAdapter(MJList);
        recyclerView.setAdapter(adapter);
    }

}

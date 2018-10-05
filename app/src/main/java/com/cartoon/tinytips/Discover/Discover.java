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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Discover extends BaseFragment<DiscoverPresenter> implements IDiscover.View{

    private Major[] major = {new Major(R.drawable.ithlw, R.drawable.jjgl),
            new Major(R.drawable.dztx,R.drawable.zzfl),
            new Major(R.drawable.gdjt,R.drawable.yssj),
            new Major(R.drawable.jxzdh,R.drawable.hyywx),
            new Major(R.drawable.jzx, R.drawable.wy),
            new Major(R.drawable.wlcl,R.drawable.hxhj),
            new Major(R.drawable.sx, R.drawable.fzfz), };

    private List<Major> MJList = new ArrayList<>();

    private MajorAdapter adapter;

    @BindView(R.id.major_recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.statusBar)
    View statusBar;

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
        recyclerList();

    }

    @Override
    protected void onPrepare(){

    }

    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }


    private void recyclerList() {
        for (Major one : major ){
            MJList.add(one);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MajorAdapter(MJList);
        recyclerView.setAdapter(adapter);
    }

}

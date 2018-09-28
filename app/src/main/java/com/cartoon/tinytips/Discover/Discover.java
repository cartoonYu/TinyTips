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

    private Major[] major = {new Major("IT互联网", R.drawable.ithlw), new Major("经济/管理", R.drawable.jjgl),
            new Major("电子/通信", R.drawable.dztx), new Major("政治/法律", R.drawable.zzfl),
            new Major("轨道/交通", R.drawable.gdjt), new Major("艺术/设计", R.drawable.yssj),
            new Major("机械/自动化", R.drawable.jxzdh), new Major("汉语言/文学", R.drawable.hyywx),
            new Major("建筑学", R.drawable.jzx), new Major("外语", R.drawable.wy),
            new Major("物理/材料", R.drawable.wlcl), new Major("化学/环境", R.drawable.hxhj),
            new Major("数学", R.drawable.sx), new Major("纺织/服装", R.drawable.fzfz)};

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

    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }

    private void removeUnderLine() {
        if (search != null) {
            try {        //--拿到字节码
                Class<?> argClass = search.getClass();
                //--指定某个私有属性,mSearchPlate是搜索框父布局的名字
                Field ownField = argClass.getDeclaredField("mSearchPlate");
                //--暴力反射,只有暴力反射才能拿到私有属性
                ownField.setAccessible(true);
                View mView = (View) ownField.get(search);
                //--设置背景
                mView.setBackgroundColor(Color.TRANSPARENT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void recyclerList() {
        for (Major one : major ){
            MJList.add(one);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MajorAdapter(MJList);
        recyclerView.setAdapter(adapter);
    }

}

package com.cartoon.tinytips.Personal.Likes;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.view.StatSocial;
import com.cartoon.tinytips.util.Adapters.Likes.LikesAdapter;
import com.cartoon.tinytips.util.UI.RevampStatusBar;
import com.cartoon.tinytips.util.UI.RevampToolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.cartoon.tinytips.util.TinyTipsApplication.getContext;

public class
Likes extends BaseActivity<LikesPresenter> implements ILikes.View{

    private LikesAdapter adapter;

    @BindView(R.id.personal_likes_likesList)
    RecyclerView recyclerView;

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.toolbarBack)
    TextView back;


    @Override
    protected LikesPresenter initPresent() {
        return new LikesPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.likes;
    }

    @Override
    protected void initView() {
        RevampToolbar.setBack(back);
        revampStatusBar();
        revampToolbar();
        initData();
    }

    @Override
    protected void onPrepare() {

    }

    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }

    private void revampToolbar(){
        RevampToolbar.setText(toolbarText,new String("我的赞"));
    }

    public void initData(){
        ArrayList<LikesItem> items = new ArrayList<LikesItem>();
        for (int i = 0;i < 15;i++){
            items.add(new LikesItem("小米8无root使用小爱AI唤醒","小米8/AI/软件","2018年12月31号"));
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new LikesAdapter(items);
        recyclerView.setAdapter(adapter);
    }


}

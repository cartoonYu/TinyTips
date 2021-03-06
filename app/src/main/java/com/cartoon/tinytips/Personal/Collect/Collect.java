package com.cartoon.tinytips.Personal.Collect;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.view.StatSocial;
import com.cartoon.tinytips.util.Adapters.Personal.Collect.CollectAdapter;
import com.cartoon.tinytips.util.FragmentConstant;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.UI.RevampStatusBar;
import com.cartoon.tinytips.util.UI.RevampToolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class Collect extends BaseActivity<CollectPresenter> implements ICollect.View{

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.toolbarBack)
    TextView back;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    private CollectAdapter collectAdapter;

    @BindView(R.id.personal_collect_collectList)
    RecyclerView collect;

    @Override
    protected CollectPresenter initPresent(){
        return new CollectPresenter(this);
    }

    @Override
    protected int getLayout(){
        return R.layout.personal_collect;
    }

    @Override
    protected void initView(){
        revampStatusBar();
        initToolbar();
        initCollect();
    }

    @Override
    protected void onPrepare(){

    }

    @Override
    public void initCollect() {
        presenter.initData();
    }

    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }

    private void initToolbar(){
        RevampToolbar.setBack(back);
        RevampToolbar.setText(toolbarText,new String("我的收藏"));
    }

    @Override
    public void initCollect(final List<StatSocial> socials){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LinearLayoutManager manager=new LinearLayoutManager(Collect.this);
                collect.setLayoutManager(manager);
                collectAdapter=new CollectAdapter(Collect.this,socials);
                collect.setAdapter(collectAdapter);
            }
        });
    }

    @OnClick(R.id.toolbarBack)
    public void onClickBack(){
        IntentActivity.intentWithData(this,Main.class,new String("main"),FragmentConstant.getConstant().getPersonal());
        IntentActivity.finishActivity(this);
    }

    @Override
    public void onBackPressed(){
        IntentActivity.intentWithData(this,Main.class,new String("main"),FragmentConstant.getConstant().getPersonal());
        IntentActivity.finishActivity(this);
    }
}

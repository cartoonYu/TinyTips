package com.cartoon.tinytips.Discover;

import android.view.View;
import android.widget.RelativeLayout;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.UI.RevampStatusBar;

import butterknife.BindView;

public class Discover extends BaseFragment<DiscoverPresenter> implements IDiscover.View{

    private DiscoverPresenter presenter;

    @BindView(R.id.statusBar)
    View statusBar;

    @Override
    protected DiscoverPresenter initPresent(){
        presenter=new DiscoverPresenter(this);
        return presenter;
    }

    @Override
    protected int getLayout(){
        return R.layout.discover;
    }

    @Override
    protected void initView(){
        revampStatusBar();
    }

    @Override
    protected void onPrepare(){

    }

    @Override
    public void revampStatusBar(){
        RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)statusBar.getLayoutParams();
        params.width=RelativeLayout.LayoutParams.MATCH_PARENT;
        params.height= RevampStatusBar.getStatusBar(getContext());
        statusBar.setLayoutParams(params);
        statusBar.setBackgroundColor(getResources().getColor(R.color.white));
    }
}

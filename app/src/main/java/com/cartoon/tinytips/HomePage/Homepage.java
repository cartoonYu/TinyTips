package com.cartoon.tinytips.HomePage;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.UI.RevampStatusBar;

import butterknife.BindView;
import butterknife.BindViews;

public class Homepage extends BaseFragment<HomepagePresenter> implements IHomepage.View{

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @Override
    protected HomepagePresenter initPresent(){
        return new HomepagePresenter(this);
    }

    @Override
    protected int getLayout(){
        return R.layout.homepage;
    }

    @Override
    protected void initView(){
        revampStatusBar();
        toolbarText.setText("首页");
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

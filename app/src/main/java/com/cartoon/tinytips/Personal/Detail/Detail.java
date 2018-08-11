package com.cartoon.tinytips.Personal.Detail;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.UI.FragmentConstant;
import com.cartoon.tinytips.util.UI.RevampStatusBar;

import butterknife.BindView;
import butterknife.OnClick;

public class Detail extends BaseActivity<DetailPresenter> implements IDetail.View{

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.toolbarBack)
    TextView back;

    @Override
    protected DetailPresenter initPresent(){
        return new DetailPresenter(this);
    }

    @Override
    protected int getLayout(){
        return R.layout.personal_detail;
    }

    @Override
    protected void initView() {
        revampStatusBar();
        back.setBackground(getResources().getDrawable(R.mipmap.personal_detail_back));
    }

    @Override
    protected void onPrepare(){

    }

    @Override
    public void intentMain(){
        Intent intent=new Intent(this, Main.class);
        intent.putExtra("main", FragmentConstant.personal);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed(){
        intentMain();
    }

    @Override
    public void revampStatusBar(){
        RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)statusBar.getLayoutParams();
        params.width=RelativeLayout.LayoutParams.MATCH_PARENT;
        params.height= RevampStatusBar.getStatusBar(this);
        statusBar.setLayoutParams(params);
        statusBar.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @OnClick(R.id.toolbarBack)
    public void onClickBack(){
        intentMain();
    }
}

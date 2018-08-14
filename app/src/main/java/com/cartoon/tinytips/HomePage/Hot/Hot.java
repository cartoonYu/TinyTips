package com.cartoon.tinytips.HomePage.Hot;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.BaseFragmentPresenter;
import com.cartoon.tinytips.R;

public class Hot extends BaseFragment<HotPresenter> implements IHot.View{

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

    }

    @Override
    protected void onPrepare() {

    }
}

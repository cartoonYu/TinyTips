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

public class Hot extends BaseFragment {
    public Hot() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_hot, container, false);
        return view;
    }

    @Override
    protected BaseFragmentPresenter initPresent() {
        return null;
    }

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onPrepare() {

    }
}

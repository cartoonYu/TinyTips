package com.cartoon.tinytips.HomePage.Favorite;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.BaseFragmentPresenter;
import com.cartoon.tinytips.R;

public class Favorite extends BaseFragment {
    public Favorite() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_favorite, container, false);
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

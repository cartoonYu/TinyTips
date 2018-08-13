package com.cartoon.tinytips.HomePage;

import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.Adapters.homepage.FragmentFavorite;
import com.cartoon.tinytips.util.Adapters.homepage.FragmentHot;
import com.cartoon.tinytips.util.Adapters.homepage.FragmentRecommend;
import com.cartoon.tinytips.util.Adapters.homepage.HomeCardAdapter;
import com.cartoon.tinytips.util.Adapters.homepage.HomeFragmentAdapter;
import com.cartoon.tinytips.util.UI.RevampStatusBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Homepage extends BaseFragment<HomepagePresenter> implements IHomepage.View,RadioGroup.OnCheckedChangeListener,ViewPager.OnPageChangeListener{

    private List<Fragment> list;
    private HomeFragmentAdapter mAdapter;

    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;

    @Nullable
    @BindView(R.id.vPager)
    ViewPager vPager;

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.rg_tab_bar)
    RadioGroup rg_tab_bar;

    @BindView(R.id.txt_topbar)
    TextView txt_topbar;

    @BindView(R.id.home_Recommend)
    RadioButton rb_recommend;

    @BindView(R.id.home_Hot)
    RadioButton rb_hot;

    @BindView(R.id.home_Favorite)
    RadioButton rb_favorite;


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
        cardPage();
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

    @Override
    public void cardPage(){
        mAdapter = new HomeFragmentAdapter(getActivity().getSupportFragmentManager());
        bindViews();
        rb_recommend.setChecked(true);
    }

    private void bindViews() {
        rg_tab_bar.setOnCheckedChangeListener(this);
        vPager.setAdapter(mAdapter);
        vPager.setCurrentItem(0);
        vPager.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.home_Recommend:
                vPager.setCurrentItem(PAGE_ONE);
                rb_recommend.setTextColor(Color.parseColor("#7ae1f7"));
                rb_recommend.setBackgroundResource(R.drawable.flowing_press_bg);
                rb_hot.setTextColor(Color.BLACK);
                rb_favorite.setTextColor(Color.BLACK);
                rb_hot.setBackgroundResource(R.drawable.flowing_unpress_bg);
                rb_favorite.setBackgroundResource(R.drawable.flowing_unpress_bg);
                break;
            case R.id.home_Hot:
                vPager.setCurrentItem(PAGE_TWO);
                rb_recommend.setTextColor(Color.BLACK);
                rb_hot.setTextColor(Color.parseColor("#7ae1f7"));
                rb_hot.setBackgroundResource(R.drawable.flowing_press_bg);
                rb_favorite.setTextColor(Color.BLACK);
                rb_recommend.setBackgroundResource(R.drawable.flowing_unpress_bg);
                rb_favorite.setBackgroundResource(R.drawable.flowing_unpress_bg);
                break;
            case R.id.home_Favorite:
                vPager.setCurrentItem(PAGE_THREE);
                rb_recommend.setTextColor(Color.BLACK);
                rb_hot.setTextColor(Color.BLACK);
                rb_favorite.setTextColor(Color.parseColor("#7ae1f7"));
                rb_favorite.setBackgroundResource(R.drawable.flowing_press_bg);
                rb_recommend.setBackgroundResource(R.drawable.flowing_unpress_bg);
                rb_hot.setBackgroundResource(R.drawable.flowing_unpress_bg);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (vPager.getCurrentItem()) {
                case PAGE_ONE:
                    rb_recommend.setChecked(true);
                    break;
                case PAGE_TWO:
                    rb_hot.setChecked(true);
                    break;
                case PAGE_THREE:
                    rb_favorite.setChecked(true);
                    break;
            }
        }
    }
}

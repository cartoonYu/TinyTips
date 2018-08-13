package com.cartoon.tinytips.util.Adapters.homepage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.cartoon.tinytips.HomePage.Homepage;

import java.util.List;

public class HomeFragmentAdapter extends FragmentPagerAdapter {
    private final int PAGER_COUNT = 3;
    private FragmentRecommend myFragment1 = null;
    private FragmentHot myFragment2 = null;
    private FragmentFavorite myFragment3 = null;


    public HomeFragmentAdapter(FragmentManager fm) {
        super(fm);
        myFragment1 = new FragmentRecommend();
        myFragment2 = new FragmentHot();
        myFragment3 = new FragmentFavorite();
    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case Homepage.PAGE_ONE:
                fragment = myFragment1;
                break;
            case Homepage.PAGE_TWO:
                fragment = myFragment2;
                break;
            case Homepage.PAGE_THREE:
                fragment = myFragment3;
                break;
        }
        return fragment;
    }

}

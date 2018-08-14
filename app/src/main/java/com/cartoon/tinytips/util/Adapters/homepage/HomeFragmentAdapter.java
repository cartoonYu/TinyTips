package com.cartoon.tinytips.util.Adapters.homepage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.cartoon.tinytips.HomePage.Favorite.Favorite;
import com.cartoon.tinytips.HomePage.Homepage;
import com.cartoon.tinytips.HomePage.Hot.Hot;
import com.cartoon.tinytips.HomePage.Recommend.Recommend;

public class HomeFragmentAdapter extends FragmentPagerAdapter {
    private final int PAGER_COUNT = 3;
    private Recommend myFragment1 = null;
    private Hot myFragment2 = null;
    private Favorite myFragment3 = null;


    public HomeFragmentAdapter(FragmentManager fm) {
        super(fm);
        myFragment1 = new Recommend();
        myFragment2 = new Hot();
        myFragment3 = new Favorite();
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

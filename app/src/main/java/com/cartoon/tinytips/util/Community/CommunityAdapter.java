package com.cartoon.tinytips.util.Community;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by cartoon on 2018/2/9.
 * 1.社区（Community）的ViewPager的适配器
 *
 * 功能：
 * 1.为社区（Community）的ViewPager提供布局以及初始化的操作
 */

public class CommunityAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    public CommunityAdapter(FragmentManager manager,List<Fragment> list){
        super(manager);
        this.list=list;
    }
    @Override
    public Fragment getItem(int position){
        return list.get(position);
    }
    @Override
    public int getCount(){
        return list.size();
    }
}

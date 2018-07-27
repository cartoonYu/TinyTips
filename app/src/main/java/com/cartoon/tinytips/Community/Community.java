package com.cartoon.tinytips.Community;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.Community.Focus.CommunityFocus;
import com.cartoon.tinytips.Community.Hot.CommunityHot;
import com.cartoon.tinytips.Community.Recommend.CommunityRecommend;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.Adapter.Community.CommunityAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by cartoon on 2018/2/28.
 * 1.社区页面，主活动（Main）的三个Fragment之一
 * 2.layout：community
 * 3.新建方法前先到ICommunity.View定义方法，再回到此类重写方法
 * 4.具体业务逻辑在CommunityPresenter中处理
 * 5.利用父类（BaseFragment）的成员变量presenter调用CommunityPresenter的成员方法进行业务逻辑的处理
 *
 * 功能：
 * 1.显示热门动态，推荐的动态和关注人动态
 * 2.提供搜索按钮供用户搜索特定的信息
 *
 * 仍需进行的操作：
 * 1.搜索按钮的点击事件到函数onClick中编写
 * 2.热门动态，推荐的动态和关注人动态数据到CommunityHot（热门），CommunityRecommend（推荐），CommunityFocus（关注）中初始化
 * 3.热门动态，推荐的动态和关注人动态的点击事件到CommunityHotAdapter（热门），
 *   CommunityRecommendAdapter（推荐），CommunityFocusAdapter（关注）中编写
 */

public class Community extends BaseFragment<CommunityPresenter>
        implements ICommunity.View,ViewPager.OnPageChangeListener{

    @BindView(R.id.toolBarBack) TextView search;            //标题栏上的搜索按钮
    @BindView(R.id.communityHot) TextView hot;               //热门标签
    @BindView(R.id.communityRecommend) TextView recommend;         //推荐标签
    @BindView(R.id.communityFocus) TextView focus;             //关注标签
    @BindView(R.id.communityFrame) ViewPager pager;           //ViewPager，用于切换热门，推荐，关注的fragment

    private PagerAdapter adapter;      //ViewPager的适配器

    private Drawable drawable;         //图片类，用于对选项卡上文字下划线进行切换

    private List<Fragment> fragmentList;       //热门，推荐，关注的fragment集合，用于ViewPager的切换
    private List<TextView> textViewList;       //热门，推荐，关注的TextV集合，用于ViewPager的切换

    @Override
    protected CommunityPresenter initPresent(){
        return new CommunityPresenter(this);
    }
    @Override
    protected int getLayout(){
        return R.layout.community;
    }
    @Override
    protected void initView(){
        pager=getActivity().findViewById(R.id.communityFrame);
        initData();
        hot.setTextColor(Color.BLUE);
        hot.setCompoundDrawables(null,null,null,drawable); //设置默认显示项提示
        search.setBackground(getResources().getDrawable(R.drawable.community_search));    //为搜索按钮更改背景
    }
    @Override
    protected void onPrepare(){
        hot.setOnClickListener(new MyOnclickListener(0));
        recommend.setOnClickListener(new MyOnclickListener(1));
        focus.setOnClickListener(new MyOnclickListener(2));
        initPager();
    }
    @Override
    public void initData(){
        fragmentList=new ArrayList<Fragment>();       //初始化fragmentList
        textViewList=new ArrayList<TextView>();        //初始化textViewList
        drawable=getResources().getDrawable(R.drawable.community_tab_point);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());//初始化drawable
        textViewList.add(hot);
        textViewList.add(recommend);
        textViewList.add(focus);
        fragmentList.add(new CommunityHot());
        fragmentList.add(new CommunityRecommend());
        fragmentList.add(new CommunityFocus());
    }
    @Override
    public void initPager(){
        adapter=new CommunityAdapter(getChildFragmentManager(),fragmentList);
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(this);
    }
    @OnClick(R.id.toolBarBack)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.toolBarBack:{
                //点击标题栏上的搜索按钮
                break;
            }
        }
    }
    @Override
    public void onPageSelected(int position){
        for(int i=0;i<textViewList.size();i++){
            if(i==position){
                textViewList.get(position).setTextColor(Color.BLUE);
                textViewList.get(position).setCompoundDrawables(null,null,null,drawable);
            }
            else{
                textViewList.get(i).setTextColor(Color.GRAY);
                textViewList.get(i).setCompoundDrawables(null,null,null,null);
            }
        }
    }
    @Override
    public void onPageScrolled(int arg0,float arg1,int arg2){
    }
    @Override
    public void onPageScrollStateChanged(int arg0) {
    }
    private class MyOnclickListener implements View.OnClickListener{
        //自定义点击事件
        private int position;
        public MyOnclickListener(int position){
            this.position=position;
        }
        @Override
        public void onClick(View v){
            pager.setCurrentItem(position);
        }
    }
}

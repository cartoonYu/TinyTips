package com.cartoon.tinytips.Community.Recommend;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.data.Note;
import com.cartoon.tinytips.util.Adapter.Community.Recommend.CommunityRecommendAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by cartoon on 2018/2/8.
 * 1.推荐，社区（Community)的子页面
 * 2.layout:community_hot
 * 3.新建方法前先到ICommunityRecommend.View定义方法，再回到此类重写方法
 * 4.具体业务逻辑在CommunityRecommendPresenter中处理
 * 5.利用父类（BaseFragment）的成员变量presenter调用CommunityRecommendPresenter的成员方法进行业务逻辑的处理
 *
 *功能：
 * 1.显示热门动态
 *
 * 仍需进行的操作：
 * 1.传入Note的集合List，并在成员函数initRecommend将其赋值给成员变量noteList，即可完成本页面的数据初始化
 * 2.关注人动态滑动列表的点击事件到CommunityRecommendAdapter处理
 */

public class CommunityRecommend extends BaseFragment<CommunityRecommendPresenter> implements ICommunityRecommend.View{

    @BindView(R.id.communityRecommendView) RecyclerView recommend;    //推荐列表

    private CommunityRecommendAdapter adapter;
    private List<Note> noteList;
    private GridLayoutManager manager;

    @Override
    protected CommunityRecommendPresenter initPresent(){
        return new CommunityRecommendPresenter(this);
    }
    @Override
    protected int getLayout(){
        return R.layout.community_recommend;
    }
    @Override
    protected void initView(){
        initRecommend();
    }
    @Override
    protected void onPrepare(){
    }
    @Override
    public void initRecommend(){
        noteList=new ArrayList<>();
        manager = new GridLayoutManager(getActivity(), 1);
        recommend.setLayoutManager(manager);
        adapter = new CommunityRecommendAdapter(noteList);
        recommend.setAdapter(adapter);
    }
}

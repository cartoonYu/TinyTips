package com.cartoon.tinytips.Community.Hot;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.data.Note;
import com.cartoon.tinytips.util.Adapter.Community.Hot.CommunityHotAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by cartoon on 2018/2/8.
 * 1.热门，社区（Community)的子页面
 * 2.layout:community_hot
 * 3.新建方法前先到ICommunityHot.View定义方法，再回到此类重写方法
 * 4.具体业务逻辑在CommunityHotPresenter中处理
 * 5.利用父类（BaseFragment）的成员变量presenter调用CommunityHotPresenter的成员方法进行业务逻辑的处理
 *
 *功能：
 * 1.显示热门动态
 *
 * 仍需进行的操作：
 * 1.传入Note的集合List，并在成员函数initHot将其赋值给成员变量noteList，即可完成本页面的数据初始化
 * 2.关注人动态滑动列表的点击事件到CommunityHotAdapter处理
 */

public class CommunityHot extends BaseFragment<CommunityHotPresenter> implements ICommunityHot.View{

    @BindView(R.id.communityHotView) RecyclerView hot;    //热门动态列表

    private CommunityHotAdapter adapter;
    private List<Note> noteList;
    private GridLayoutManager manager;

    @Override
    protected CommunityHotPresenter initPresent(){
        return new CommunityHotPresenter(this);
    }
    @Override
    protected int getLayout(){
        return R.layout.community_hot;
    }
    @Override
    protected void initView(){
        initHot();
    }
    @Override
    protected void onPrepare(){

    }
    @Override
    public void initHot(){
        //对热门动态列表进行初始化
        noteList=new ArrayList<>();
        manager = new GridLayoutManager(getActivity(), 1);
        hot.setLayoutManager(manager);
        adapter = new CommunityHotAdapter(noteList);
        hot.setAdapter(adapter);
    }
}

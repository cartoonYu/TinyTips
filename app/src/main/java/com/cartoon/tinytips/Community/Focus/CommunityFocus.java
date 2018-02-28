package com.cartoon.tinytips.Community.Focus;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.data.TableNote.Note;
import com.cartoon.tinytips.util.Community.Focus.CommunityFocusAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cartoon on 2018/2/8.
 * 1.关注，社区（Community)的子页面
 * 2.layout:community_focus
 * 3.新建方法前先到ICommunityFocus.View定义方法，再回到此类重写方法
 * 4.具体业务逻辑在CommunityFocusPresenter中处理
 * 5.利用父类（BaseFragment）的成员变量presenter调用CommunityFocusPresenter的成员方法进行业务逻辑的处理
 *
 *功能：
 * 1.显示用户关注人的动态
 *
 * 仍需进行的操作：
 * 1.传入Note的集合List，并在成员函数initFocus将其赋值给成员变量noteList，即可完成本页面的数据初始化
 * 2.关注人动态滑动列表的点击事件到CommunityFocusAdapter处理
 */

public class CommunityFocus extends BaseFragment<CommunityFocusPresenter> implements ICommunityFocus.View{

    private RecyclerView focus;    //关注人动态列表

    private CommunityFocusAdapter adapter;          //关注人动态滚动列表适配器
    private List<Note> noteList;                   //关注人动态滚动列表的数据集合
    private GridLayoutManager manager;             //关注人动态滚动列表布局管理器

    @Override
    protected CommunityFocusPresenter initPresent(){
        return new CommunityFocusPresenter(this);
    }
    @Override
    protected int getLayout(){
        return R.layout.community_focus;
    }
    @Override
    protected void initView(){
        focus=getActivity().findViewById(R.id.communityFocusView);
        initFocus();
    }
    @Override
    protected void onPrepare(){

    }
    @Override
    public void initFocus(){
        //对关注人动态列表进行初始化
        noteList=new ArrayList<>();
        manager = new GridLayoutManager(getActivity(), 1);
        focus.setLayoutManager(manager);
        adapter = new CommunityFocusAdapter(noteList);
        focus.setAdapter(adapter);
    }
}

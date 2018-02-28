package com.cartoon.tinytips.HomePage;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.DbUtil.MyDatabaseHelper;
import com.cartoon.tinytips.HomePage.AddNote.AddNote;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.data.TableNote.Note;
import com.cartoon.tinytips.util.HomePage.HomePageNoteAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cartoon on 2018/2/4.
 *
 * 1.首页页面，主活动（Main）的三个Fragment之一
 * 2.layout：personal
 * 3.新建方法前先到IHomePage.View定义方法，再回到此类重写方法
 * 4.具体业务逻辑在HomePagePresenter中处理
 * 5.利用父类（BaseFragment）的成员变量presenter调用HomePagePresenter的成员方法进行业务逻辑的处理
 *
 *功能：
 * 1.显示用户所做的笔记
 * 2.点击标题栏的搜索按钮可以对笔记进行分类或者搜索特定的笔记，并将结果返回到标题栏下方的滑动列表上
 * 3.点击标题栏的添加按钮跳转到新建笔记页面，新增笔记
 *
 * 仍需进行的操作：
 * 1.传入Note的集合List，并在成员函数initNote将其赋值给成员变量noteList，即可完成首页的页面的数据初始化
 * 2.页面中标题栏上的搜索以及新建笔记的页面跳转逻辑，需要在函数onClick实现
 * 3.笔记滑动列表的点击事件到HomePageAdapter处理
 */

public class HomePage extends BaseFragment<HomePagePresenter> implements IHomePage.View,View.OnClickListener{

    private TextView back;              //标题栏的返回按钮，因为此页面没有返回功能，所以定义此变量目的是把返回符号去掉
    private TextView addNote;           //标题栏上的添加按钮
    private TextView search;            //标题栏上的搜索按钮

    private RecyclerView note;          //日记滚动列表

    private HomePageNoteAdapter adapter;          //日记滚动列表适配器
    private List<Note> noteList;                  //日记滚动列表的数据集合
    private GridLayoutManager manager;            //日记滚动列表布局管理器

    private Intent intent;

    @Override
    protected int getLayout(){
        return R.layout.homepage;
    }
    @Override
    protected HomePagePresenter initPresent(){
        return new HomePagePresenter(this);
    }
    @Override
    protected void initView(){
        back=getActivity().findViewById(R.id.toolBarBack);
        addNote=getActivity().findViewById(R.id.tooBarTool1);
        search=getActivity().findViewById(R.id.tooBarTool2);
        note=getActivity().findViewById(R.id.homePageNote);
        back.setBackgroundColor(getResources().getColor(R.color.ashBlack));
        addNote.setBackground(getResources().getDrawable(R.drawable.homepage_addnote));
        search.setBackground(getResources().getDrawable(R.drawable.homepage_search));
        initNote();
    }
    @Override
    protected void onPrepare(){
        search.setOnClickListener(this);
        addNote.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tooBarTool2:{
                //点击标题栏上的搜索按钮
                break;
            }
            case R.id.tooBarTool1:{
                //点击标题栏上的添加按钮
                intent=new Intent(getActivity(), AddNote.class);
                startActivity(intent);
                getActivity().finish();
                break;
            }
        }
    }
    @Override
    public void initNote() {
        //初始化笔记列表
        noteList=new ArrayList<>();
        noteList=presenter.getNoteList();
        manager = new GridLayoutManager(getActivity(), 1);
        note.setLayoutManager(manager);
        adapter = new HomePageNoteAdapter(noteList);
        note.setAdapter(adapter);
    }

    @Override
    public MyDatabaseHelper getMyDatabaseHelper() {
        return new MyDatabaseHelper(getActivity(),"TinyTips.db",null,1);
    }
    @Override
    public void showToast(String code){
        Toast.makeText(getActivity(),code,Toast.LENGTH_SHORT).show();
    }
}

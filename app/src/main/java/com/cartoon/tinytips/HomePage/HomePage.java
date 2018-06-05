package com.cartoon.tinytips.HomePage;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.HomePage.AddNote.AddNote;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.HomePage.HomePageNoteAdapter;
import com.cartoon.tinytips.util.TinyTipsApplication;

import butterknife.BindView;

/**
 * Created by cartoon on 2018/2/4.
 *
 * 1.首页页面，主活动（Main）的三个Fragment之一
 * 2.layout：homepage
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

    //private TextView back;              //标题栏的返回按钮，因为此页面没有返回功能，所以定义此变量目的是把返回符号去掉
    private TextView addNote;           //标题栏上的添加按钮
    private TextView search;            //标题栏上的搜索按钮

    private RecyclerView note;          //日记滚动列表

    private HomePageNoteAdapter adapter;          //日记滚动列表适配器
    private GridLayoutManager manager;            //日记滚动列表布局管理器

    private Intent intent;

    private AlertDialog.Builder builder;
    private LayoutInflater inflater;
    private View v;

    @BindView(R.id.toolBarBack) TextView back;

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
                handleClickSearch();
                break;
            }
            case R.id.tooBarTool1:{
                //点击标题栏上的添加按钮
                handleClickAdd();
                break;
            }
        }
    }
    @Override
    public void handleClickSearch(){
        inflater=LayoutInflater.from(TinyTipsApplication.getContext());
        v=inflater.inflate(R.layout.homepage_search, null);
        final EditText search=v.findViewById(R.id.homePageSearch);
        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("请输入分类");
        builder.setView(v);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //搜索逻辑在这里编写
                presenter.search(search.getText().toString());
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }
    @Override
    public void handleClickAdd(){
        intent=new Intent(getActivity(), AddNote.class);
        startActivity(intent);
        getActivity().finish();
    }
    @Override
    public void initNote() {
        //初始化笔记列表
        presenter.initData();
        manager = new GridLayoutManager(getActivity(), 1);
        note.setLayoutManager(manager);
        adapter = new HomePageNoteAdapter(presenter.getNoteList());
        note.setAdapter(adapter);
    }
    @Override
    public void refreshAdapter(){
        adapter.notifyDataSetChanged();
    }
    @Override
    public void showToast(String code){
        Toast.makeText(getActivity(),code,Toast.LENGTH_SHORT).show();
    }
}

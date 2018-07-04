package com.cartoon.tinytips.Personal.Collect;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.Adapter.Personal.PersonalCollectAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by cartoon on 2018/3/5.
 * 1.我的收藏页面
 * 2.layout：personal_collect
 * 3.新建方法前先到IPersonalCollect.View定义方法，再回到此类重写方法
 * 4.具体业务逻辑在PersonalCollectPresenter中处理
 * 5.利用父类（BaseActivity）的成员变量presenter调用PersonalCollectPresenter的成员方法进行业务逻辑的处理
 *
 *功能：
 * 1.显示用户所收藏的笔记
 *
 * 仍需进行的操作：
 * 1.在PersonalCollectPresenter中的initData对其成员变量noteList进行赋值即可完成页面数据的初始化
 * 2.笔记滑动列表的点击事件到PersonalCollectAdapter处理
 */

public class Collect extends BaseActivity<CollectPresenter>
        implements ICollect.View{

    @BindView(R.id.toolBarTag) TextView tag;
    @BindView(R.id.personalCollectNote) RecyclerView note;          //日记滚动列表

    private PersonalCollectAdapter adapter;          //日记滚动列表适配器
    private GridLayoutManager manager;            //日记滚动列表布局管理器

    private Intent intent;

    @Override
    protected CollectPresenter initPresent(){
        return new CollectPresenter(this);
    }
    @Override
    protected int getLayout(){
        return R.layout.personal_collect;
    }
    @Override
    protected void initView(){
        tag.setText("收藏");
        initNote();
    }
    @Override
    protected void onPrepare(){
    }


    @OnClick(R.id.toolBarBack)
    public void handleClickBack(){
        //点击标题栏上的返回按钮
        intent=new Intent(this, Main.class);
        intent.putExtra("flag",2);
        startActivity(intent);
        finish();
    }

    @Override
    public void initNote() {
        //初始化笔记列表
        presenter.initData();
        manager = new GridLayoutManager(this, 1);
        note.setLayoutManager(manager);
        adapter = new PersonalCollectAdapter(presenter.getNoteList());
        note.setAdapter(adapter);
    }

    @Override
    public void refreshAdapter(){
        adapter.notifyDataSetChanged();
    }
    @Override
    public void showToast(String code){
        Toast.makeText(this,code,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed(){
        handleClickBack();
    }
}
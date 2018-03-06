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
import com.cartoon.tinytips.util.Personal.PersonalCollectAdapter;

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

public class PersonalCollect extends BaseActivity<PersonalCollectPresenter>
        implements IPersonalCollect.View,View.OnClickListener{

    private TextView back;
    private TextView tag;

    private RecyclerView note;          //日记滚动列表

    private PersonalCollectAdapter adapter;          //日记滚动列表适配器
    private GridLayoutManager manager;            //日记滚动列表布局管理器

    private Intent intent;

    @Override
    protected PersonalCollectPresenter initPresent(){
        return new PersonalCollectPresenter(this);
    }
    @Override
    protected int getLayout(){
        return R.layout.personal_collect;
    }
    @Override
    protected void initView(){
        back=findViewById(R.id.toolBarBack);
        tag=findViewById(R.id.toolBarTag);
        note=findViewById(R.id.personalCollectNote);
        tag.setText("收藏");
        initNote();
    }
    @Override
    protected void onPrepare(){
        back.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.toolBarBack:{
                handleClickBack();
                break;
            }
        }
    }
    @Override
    public void handleClickBack(){
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

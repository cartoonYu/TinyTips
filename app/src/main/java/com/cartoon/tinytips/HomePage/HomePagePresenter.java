package com.cartoon.tinytips.HomePage;

import android.database.sqlite.SQLiteDatabase;

import com.cartoon.tinytips.BaseFragmentPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.data.TableNote.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cartoon on 2018/2/6.
 * 1.首页（HomePage）的Presenter
 *
 * 功能
 * 1.为首页（HomePage）以及数据库提供中间处理层
 *
 * 操作：
 * 1.定义函数并在View进行调用，利用成员变量view操作View层的函数，实现了View与Presenter的双向调用
 * 2.HomePagePresenter所有函数应到IHomePage.Presenter定义再在此重写方法
 */

class HomePagePresenter extends BaseFragmentPresenter<HomePage> implements IHomePage.Presenter{
    private IHomePage.View view;
    private IHomePage.Model model;
    private List<Note> noteList;
    public HomePagePresenter(IHomePage.View view){
        this.view=view;
        model=new HomePageModel(view.getMyDatabaseHelper().getWritableDatabase());
        noteList=new ArrayList<>();
    }
    @Override
    public void initData(){
        //为View层的初始化提供数据
        noteList=model.getHomePageMessages(new ValueCallBack<List<Note>>() {
            @Override
            public void onSuccess(List<Note> notes) {
                noteList=notes;
            }

            @Override
            public void onFail(String code) {
                view.showToast(code);
            }
        });
    }
    public List<Note> getNoteList(){
        return noteList;
    }
}

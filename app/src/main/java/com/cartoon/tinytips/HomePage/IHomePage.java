package com.cartoon.tinytips.HomePage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.cartoon.tinytips.DbUtil.MyDatabaseHelper;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.data.TableNote.Note;

import java.util.List;

/**
 * Created by cartoon on 2018/2/4.
 * HomePage的公共接口
 * View类接入IHomePage.view，Presenter类接入IHomePage.Presenter
 * 在内部接口定义方法，在接入类中重写方法实现功能
 */

interface IHomePage {
    interface View{
        void initNote();       //初始化笔记列表
        //void refreshAdapter();
        void handleClickSearch();
        void handleClickAdd();
        void showToast(String code);
    }
    interface Presenter{
        void initData();
        void search(String word);
    }
    interface Model{
        void getHomePageMessages(ValueCallBack<List<Note>> callBack);
        void setWord(String word);
        void searchData(ValueCallBack<List<Note>> callBack);
    }
}

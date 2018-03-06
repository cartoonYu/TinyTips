package com.cartoon.tinytips.HomePage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cartoon.tinytips.DbUtil.DatabaseUtil;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.data.TableNote.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 许锦鹏 on 2018/2/24.
 */

public class HomePageModel implements IHomePage.Model {

    private List<Note> noteList;

    public HomePageModel() {
        super();
        noteList=new ArrayList<>();
    }

    @Override
    public void getHomePageMessages(ValueCallBack<List<Note>> callBack) {
        noteList= DatabaseUtil.queryNote("Note",
                null,null,null,null,null,null,null);

        if (noteList.isEmpty()){
            callBack.onFail("笔记初始化失败，请重试");
        }else {
            callBack.onSuccess(noteList);
        }
    }
}

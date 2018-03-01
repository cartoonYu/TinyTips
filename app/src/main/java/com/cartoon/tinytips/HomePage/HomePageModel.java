package com.cartoon.tinytips.HomePage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
    private Context context;

    public HomePageModel(Context context) {
        super();
        this.context=context;
        noteList=new ArrayList<>();
    }

    @Override
    public void getHomePageMessages(ValueCallBack<List<Note>> callBack) {
        noteList= DatabaseUtil.queryNote(context,"note",
                null,null,null,null,null,"date",null);
        if (noteList.isEmpty()){
            callBack.onFail("笔记初始化失败，请重试");
        }else {
            callBack.onSuccess(noteList);
        }
    }
}

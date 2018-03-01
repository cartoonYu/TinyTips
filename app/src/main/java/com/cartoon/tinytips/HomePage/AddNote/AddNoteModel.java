package com.cartoon.tinytips.HomePage.AddNote;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.cartoon.tinytips.DbUtil.DatabaseUtil;
import com.cartoon.tinytips.DbUtil.MyDatabaseHelper;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.data.TableNote.Note;

import java.util.Date;
import java.util.logging.Handler;

/**
 * Created by 许锦鹏 on 2018/2/23
 */

public class AddNoteModel implements IAddNote.Model {
    private Note note;

    @Override
    public void addNote(final ValueCallBack<String> callBack) {
        if ((note.getDetails().equals(null))&&note.getAuthor().equals(null)){
            callBack.onFail("笔记新建失败，请重试");
        }else{
            new android.os.Handler().post(new Runnable() {
                @Override
                public void run() {
                    DatabaseUtil.insert(note);
                }
            });
            callBack.onSuccess("新建笔记成功");
        }
    }

    @Override
    public void setNote(String title, String date, String author, String imageDetails,String details, String[] classify, boolean isCollect) {
        note=new Note(title,date, author, imageDetails,details, classify ,isCollect);
    }
}

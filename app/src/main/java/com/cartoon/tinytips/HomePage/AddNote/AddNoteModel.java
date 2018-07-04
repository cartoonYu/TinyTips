package com.cartoon.tinytips.HomePage.AddNote;

import com.cartoon.tinytips.util.DbUtil.DatabaseUtil;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.data.Note;
import com.cartoon.tinytips.util.JudgeObjectIsEmpty;

/**
 * Created by 许锦鹏 on 2018/2/23
 */

public class AddNoteModel implements IAddNote.Model {
    private Note note;

    @Override
    public void addNote(final ValueCallBack<String> callBack) {
        if (JudgeObjectIsEmpty.isNotEmpty(note)){
            new android.os.Handler().post(new Runnable() {
                @Override
                public void run() {
                    DatabaseUtil.insert(note);
                }
            });
            callBack.onSuccess("新建笔记成功");
        }
        else{
            callBack.onFail("笔记新建失败，请重试");
        }
    }

    @Override
    public void setNote(String title, String date, String author, String imageDetails,String details, String[] classify, boolean isCollect) {
        note=new Note(title,date, author, imageDetails,details, classify ,isCollect);
    }
}

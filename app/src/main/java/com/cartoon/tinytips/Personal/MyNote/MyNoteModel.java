package com.cartoon.tinytips.Personal.MyNote;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Note;

public class MyNoteModel implements IMyNote.Model {
    @Override
    public void getMyNoteInformation(ValueCallBack<Note> callBack){
        Note mynote = new Note();
        if (mynote != null){
            callBack.onFail("获取笔记失败");
        }else {
            callBack.onSuccess(mynote);
        }
    }
}

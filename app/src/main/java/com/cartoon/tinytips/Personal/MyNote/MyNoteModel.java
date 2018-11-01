package com.cartoon.tinytips.Personal.MyNote;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Operate.OperateNote;
import com.cartoon.tinytips.util.JudgeEmpty;

import java.util.List;

public class MyNoteModel implements IMyNote.Model {

    private OperateNote operateNote;

    @Override
    public void getNote(Information information,ValueCallBack<List<Note>> callBack){
        Note note=new Note();
        note.setAuthor(information.getNickName());
        List<Note> notes=operateNote.query(note);
        if(JudgeEmpty.isEmpty(note)||notes.isEmpty()){
            callBack.onFail("获取笔记信息失败");
        }
        else {
            callBack.onSuccess(notes);
        }
    }

    public MyNoteModel(){
        operateNote=OperateNote.getOperateNote();
    }
}

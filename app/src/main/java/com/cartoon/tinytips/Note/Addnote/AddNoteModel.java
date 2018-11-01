package com.cartoon.tinytips.Note.Addnote;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Operate.OperateNote;
import com.cartoon.tinytips.util.JudgeEmpty;

public class AddNoteModel implements IAddNote.Model {

    private OperateNote operateNote;

    @Override
    public void addNote(Note note, ValueCallBack<String> callBack){
        if(JudgeEmpty.isEmpty(note)){
            callBack.onFail("新建笔记失败");
        }
        note.setAuthor("Leo");
        note.setUserId(12);
        if(operateNote.add(note)){
            callBack.onSuccess("新建笔记成功");
        }
        else {
            callBack.onFail("新建笔记失败");
        }
    }

    public AddNoteModel(){
        operateNote=OperateNote.getOperateNote();
    }
}

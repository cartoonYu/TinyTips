package com.cartoon.tinytips.Note.RevampNote;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Operate.OperateNote;

public class RevampNoteModel implements IRevampNote.Model{

    private OperateNote operateNote;

    @Override
    public void revampNote(Note oldNote, Note note, ValueCallBack<String> callBack) {
        operateNote.update(oldNote,note);
        while (operateNote.isNotFinish()){
        }
        if(operateNote.isSuccess()){
            callBack.onSuccess("修改成功");
        }
        else {
            callBack.onFail("修改失败");
        }
    }

    public RevampNoteModel(){
        operateNote=OperateNote.getOperateNote();
    }
}

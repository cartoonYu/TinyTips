package com.cartoon.tinytips.Note.RevampNote;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Note;
import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.table.Operate.OperateNote;
import com.cartoon.tinytips.bean.table.Operate.imp.IOperateNote;

public class RevampNoteModel implements IRevampNote.Model{

    private IOperateNote operateNote;

    @Override
    public void revampNote(Note oldNote, Note note,final ValueCallBack<String> callBack) {
        operateNote.update(oldNote, note, new IOperateBean<String>() {
            @Override
            public void onSuccess(String s) {
                callBack.onSuccess("修改成功");
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail("修改失败");
            }
        });
    }

    public RevampNoteModel(){
        operateNote=OperateNote.getOperateNote();
    }
}

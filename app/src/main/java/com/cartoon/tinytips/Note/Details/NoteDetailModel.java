package com.cartoon.tinytips.Note.Details;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Default_many;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Local.LocalInformation;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Operate.OperateNote;
import com.cartoon.tinytips.util.JudgeEmpty;

import java.util.List;

public class NoteDetailModel implements INoteDetail.Model {

    private OperateNote operateNote;

    private Information information;

    public NoteDetailModel() {
        operateNote = OperateNote.getOperateNote();
        information = LocalInformation.getLocalInformation().query();
    }

    @Override
    public void getNote(ValueCallBack<Note> callBack) {
        Note note = new Note();
        note.setUserId(information.getId());
        operateNote.query(note);
        while (operateNote.isNotFinish()){

        }
        List<Note> notes= operateNote.getQueryData();
        note = notes.get(0);

        if(notes.isEmpty()){
            callBack.onFail("获取笔记内容失败");
        }
        else {
            callBack.onSuccess(note);
        }
    }
}

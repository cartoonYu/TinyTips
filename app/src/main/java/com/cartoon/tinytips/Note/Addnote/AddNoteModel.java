package com.cartoon.tinytips.Note.Addnote;

import android.print.PrinterId;
import android.util.Log;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Local.LocalInformation;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Operate.OperateNote;
import com.cartoon.tinytips.util.JudgeEmpty;

public class AddNoteModel implements IAddNote.Model {

    private OperateNote operateNote;

    private Information information;

    @Override
    public void addNote(Note note, ValueCallBack<String> callBack){
        if(JudgeEmpty.isEmpty(note)){
            callBack.onFail("新建笔记失败");
        }
        note.setUserId(information.getId());
        note.setAuthor(information.getNickName());
        operateNote.add(note);
        while (operateNote.isNotFinish()){

        }
        if(operateNote.isSuccess()){
            callBack.onSuccess("新建笔记成功");
        }
        else {
            callBack.onFail("新建笔记失败");
        }
    }

    public AddNoteModel(){
        operateNote =OperateNote.getOperateNote();
        information=LocalInformation.getLocalInformation().query();
    }
}

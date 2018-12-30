package com.cartoon.tinytips.Note.Addnote;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.bean.table.Local.LocalInformation;
import com.cartoon.tinytips.bean.table.Note;
import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.table.Operate.OperateNote;
import com.cartoon.tinytips.util.JudgeEmpty;

public class AddNoteModel implements IAddNote.Model {

    private OperateNote operateNote;

    private Information information;

    @Override
    public void addNote(Note note,final ValueCallBack<String> callBack){
        if(JudgeEmpty.isEmpty(note)){
            callBack.onFail("新建笔记失败");
        }
        note.setUserId(information.getId());
        note.setAuthor(information.getNickName());
        operateNote.add(note, new IOperateBean<String>() {
            @Override
            public void onSuccess(String s) {
                callBack.onSuccess("新建笔记成功");
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail("新建笔记失败");
            }
        });
    }

    public AddNoteModel(){
        operateNote =OperateNote.getOperateNote();
        information=LocalInformation.getLocalInformation().query();
    }
}

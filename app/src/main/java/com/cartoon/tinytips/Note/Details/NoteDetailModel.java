package com.cartoon.tinytips.Note.Details;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.bean.table.Local.LocalInformation;
import com.cartoon.tinytips.bean.table.Note;
import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.table.Operate.OperateNote;
import com.cartoon.tinytips.bean.table.Operate.imp.IOperateNote;
import com.cartoon.tinytips.bean.view.StatSocial;
import com.cartoon.tinytips.bean.view.check.CheckStatSocial;
import com.cartoon.tinytips.bean.view.check.imp.ICheckStatSocial;
import com.cartoon.tinytips.util.JudgeEmpty;

import java.util.List;

public class NoteDetailModel implements INoteDetail.Model {

    private IOperateNote operateNote;

    private ICheckStatSocial checkStatSocial;

    @Override
    public void getNote(StatSocial social, final ValueCallBack<Note> callBack) {
        if(JudgeEmpty.isEmpty(social)){
            callBack.onFail("系统错误");
        }
        Note note=new Note();
        note.setId(social.getNoteId());
        operateNote.query(note, new IOperateBean<List<Note>>() {
            @Override
            public void onSuccess(List<Note> notes) {
                callBack.onSuccess(notes.get(0));
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail("查询失败");
            }
        });
    }

    @Override
    public void getSocial(Note note, final ValueCallBack<StatSocial> callBack) {
        if(JudgeEmpty.isEmpty(note)){
            callBack.onFail("系统错误");
        }
        StatSocial social=new StatSocial();
        social.setNoteId(note.getId());
        checkStatSocial.query(social, new IOperateBean<List<StatSocial>>() {
            @Override
            public void onSuccess(List<StatSocial> socials) {
                callBack.onSuccess(socials.get(0));
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail("查询失败");
            }
        });
    }

    public NoteDetailModel() {
        operateNote = OperateNote.getOperateNote();
        checkStatSocial=CheckStatSocial.getCheckStatSocial();
    }
}

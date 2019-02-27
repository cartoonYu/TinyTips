package com.cartoon.tinytips.Personal.MyNote;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.bean.table.Local.LocalInformation;
import com.cartoon.tinytips.bean.table.Note;
import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.table.Operate.OperateNote;
import com.cartoon.tinytips.bean.table.Operate.imp.IOperateNote;
import com.cartoon.tinytips.util.JudgeEmpty;

import java.util.List;

public class MyNoteModel implements IMyNote.Model {

    private IOperateNote operateNote;

    private Information information;

    @Override
    public void getNote(final ValueCallBack<List<Note>> callBack){
        Note note=new Note();
        note.setUserId(information.getId());
        operateNote.query(note, new IOperateBean<List<Note>>() {
            @Override
            public void onSuccess(List<Note> list) {
                callBack.onSuccess(list);
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail("获取笔记信息失败");
            }
        });
    }

    @Override
    public void deleteNote(final List<Note> list,final Note note,final ValueCallBack<String> callBack) {
        if(JudgeEmpty.isEmpty(note)){
            callBack.onFail("系统错误，请重试");
            return;
        }
        operateNote.delete(note, new IOperateBean<String>() {
            @Override
            public void onSuccess(String s) {
                list.remove(note);
                callBack.onSuccess("删除成功");
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail("删除失败，请重试");
            }
        });
    }

    public MyNoteModel(){
        operateNote =OperateNote.getOperateNote();
        information=LocalInformation.getLocalInformation().query();
    }
}

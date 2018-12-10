package com.cartoon.tinytips.Personal.MyNote;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Local.LocalInformation;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Operate.OperateNote;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.ShowToast;

import org.json.JSONObject;

import java.util.List;

public class MyNoteModel implements IMyNote.Model {

    private OperateNote operateNote;

    private Information information;

    @Override
    public void getNote(ValueCallBack<List<Note>> callBack){
        Note note=new Note();
        note.setUserId(information.getId());
        operateNote.query(note);
        while (operateNote.isNotFinish()){

        }
        List<Note> notes= operateNote.getQueryData();
        if(notes.isEmpty()){
            callBack.onFail("获取笔记信息失败");
        }
        else {
            callBack.onSuccess(notes);
        }
    }

    @Override
    public void deleteNote(List<Note> list,Note note, ValueCallBack<String> callBack) {
        if(JudgeEmpty.isEmpty(note)){
            callBack.onFail("系统错误，请重试");
            return;
        }
        operateNote.delete(note);
        while (operateNote.isNotFinish()){
            ShowToast.shortToast("正在删除中");
        }
        if(operateNote.isSuccess()){
            list.remove(note);
            callBack.onSuccess("删除成功");
        }
        else {
            callBack.onFail("删除失败，请重试");
        }
    }

    public MyNoteModel(){
        operateNote =OperateNote.getOperateNote();
        information=LocalInformation.getLocalInformation().query();
    }
}

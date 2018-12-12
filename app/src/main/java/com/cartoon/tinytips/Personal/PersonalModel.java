package com.cartoon.tinytips.Personal;

import android.util.Log;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Local.LocalInformation;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Operate.OperateNote;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.ShowToast;

import java.util.List;

class PersonalModel implements IPersonal.Model {

    private String NumOfNote;

    private String NumOfAttentions;

    private String NumOfFans;

    private List<Information> list;

    private Information info;


    private OperateNote operateNote;

    public PersonalModel() {
        operateNote =OperateNote.getOperateNote();
        info=LocalInformation.getLocalInformation().query();
    }

    @Override
    public void getPersonalInformation(ValueCallBack<Information> callBack){
        if (JudgeEmpty.isEmpty(info)){
            callBack.onFail("获取个人信息失败");
        }else {
            callBack.onSuccess(info);
        }
    }

    @Override
    public void getNoteNum(ValueCallBack<Integer> callBack){
        Note note=new Note();
        note.setUserId(info.getId());
        operateNote.query(note);
        while (operateNote.isNotFinish()){
            ShowToast.shortToast("获取笔记信息中");
        }
        List<Note> notes= operateNote.getQueryData();
        if(JudgeEmpty.isEmpty(notes)){
            callBack.onFail("获取笔记信息失败");
        }
        else {
            callBack.onSuccess(notes.size());
        }
    }

}

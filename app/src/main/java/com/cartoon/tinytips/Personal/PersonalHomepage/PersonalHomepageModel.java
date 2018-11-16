package com.cartoon.tinytips.Personal.PersonalHomepage;

import android.util.Log;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Local.LocalInformation;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Operate.OperateNote;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.ShowToast;

import java.util.List;

public class PersonalHomepageModel implements IPersonalHomepage.Model {

    private Information information;

    private Note queryCondition;

    private List<Note> noteList;

    private OperateNote operateNote;

    @Override
    public void getInformation(ValueCallBack<Information> callBack) {
        information=LocalInformation.getLocalInformation().query();
        if(JudgeEmpty.isEmpty(information)){
            callBack.onFail("获取个人信息失败");
        }
        else {
            callBack.onSuccess(information);
        }
    }

    @Override
    public void getNoteList(ValueCallBack<List<Note>> callBack) {
        queryCondition = new Note();
        queryCondition.setUserId(information.getId());
        operateNote.query(queryCondition);
        while (operateNote.isNotFinish()){
            ShowToast.shortToast("获取笔记信息中");
        }
        noteList= operateNote.getQueryData();
        if(JudgeEmpty.isEmpty(noteList)){
            callBack.onFail("获取笔记信息失败");
        }
        else {
            callBack.onSuccess(noteList);
        }
    }

    public PersonalHomepageModel(){
        operateNote =OperateNote.getOperateNote();
    }
}

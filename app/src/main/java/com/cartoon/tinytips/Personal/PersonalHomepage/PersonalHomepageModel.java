package com.cartoon.tinytips.Personal.PersonalHomepage;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Operate.OperateNote;

import java.util.List;

public class PersonalHomepageModel implements IPersonalHomepage.Model {

    private Information information;

    private Note queryCondition;

    private List<Note> noteList;

    private OperateNote operateNote;

    @Override
    public void getHomepageInformation(ValueCallBack<Note> callBack) {

    }

    @Override
    public void getHomepagePersonalInformation(ValueCallBack<Information> callBack) {
        if (information!=null){
            callBack.onSuccess(information);

        }else {
            callBack.onFail("获取个人信息失败");
        }
    }

    @Override
    public void setInformation(Information information) {
        this.information = information;
    }

    @Override
    public void getNoteList(ValueCallBack<List<Note>> valueCallBack) {
        queryCondition = new Note();
        queryCondition.setAuthor(information.getNickName());
        operateNote.query(queryCondition);
        while (operateNote.isNotFinish()){

        }
        noteList= operateNote.getQueryData();
        if (noteList!=null){
            valueCallBack.onSuccess(noteList);
        }else{
            valueCallBack.onFail("获取笔记信息失败");
        }
    }

    public PersonalHomepageModel(){
        operateNote =OperateNote.getOperateNote();
    }
}

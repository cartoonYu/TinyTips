package com.cartoon.tinytips.Personal;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Operate.OperateInformation;
import com.cartoon.tinytips.bean.Operate.OperateNote;
import com.cartoon.tinytips.util.JudgeEmpty;

import java.util.List;

class PersonalModel implements IPersonal.Model {

    private String NumOfNote;

    private String NumOfAttentions;

    private String NumOfFans;

    private List<Information> list;

    private Information info;

    private OperateInformation operateInformation;

    private OperateNote operateNote;

    public PersonalModel() {
        operateInformation = OperateInformation.getOperate();
        operateNote=OperateNote.getOperateNote();
    }

    @Override
    public void getPersonalInformation(ValueCallBack<Information> callBack){
        if (info!=null){
            callBack.onSuccess(info);
        }else {
            callBack.onFail("获取个人信息失败");
        }
    }

    @Override
    public void getNoteNum(Information information,ValueCallBack<Integer> callBack){
        if(JudgeEmpty.isEmpty(information)|| JudgeEmpty.isEmpty(information.getNickName())){
            callBack.onFail("获取笔记信息失败");
        }
        Note note=new Note();
        note.setAuthor(information.getNickName());
        List<Note> notes=operateNote.query(note);
        if(JudgeEmpty.isNotEmpty(notes)){
            callBack.onSuccess(notes.size());
        }
    }

    public void setInformation(Information information){
        info = information;
    }
}

package com.cartoon.tinytips.Personal;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.bean.table.Local.LocalInformation;
import com.cartoon.tinytips.bean.table.Note;
import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.table.Operate.OperateNote;
import com.cartoon.tinytips.util.JudgeEmpty;

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
    public void getNoteNum(final ValueCallBack<Integer> callBack){
        Note note=new Note();
        note.setUserId(info.getId());
        operateNote.query(note, new IOperateBean<List<Note>>() {
            @Override
            public void onSuccess(List<Note> list) {
                callBack.onSuccess(list.size());
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail("获取笔记信息失败");
            }
        });
    }

}

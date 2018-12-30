package com.cartoon.tinytips.Personal.PersonalHomepage;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Local.LocalInformation;
import com.cartoon.tinytips.bean.table.Note;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.bean.table.Operate.OperateComment;
import com.cartoon.tinytips.bean.table.Operate.OperateNote;
import com.cartoon.tinytips.bean.table.Operate.OperateSocial;
import com.cartoon.tinytips.util.Adapters.Personal.PersonalHomepage.DynamicState;
import com.cartoon.tinytips.util.JudgeEmpty;

import java.util.List;

public class PersonalHomepageModel implements IPersonalHomepage.Model {

    private Information info;

    private Note queryCondition;

    private List<Note> noteList;

    private OperateNote operateNote;

    private OperateSocial operateSocial;

    private OperateComment operateComment;

    @Override
    public void getInformation(Information information,ValueCallBack<Information> callBack) {
        if(JudgeEmpty.isNotEmpty(information)){
            info=information;
        }
        if(JudgeEmpty.isEmpty(info)){
            callBack.onFail("获取个人信息失败");
        }
        else {
            callBack.onSuccess(info);
        }
    }

    @Override
    public void getDynamicStateList(ValueCallBack<List<DynamicState>> callBack) {
        callBack.onFail("系统错误");
    }

    public PersonalHomepageModel(){
        operateNote =OperateNote.getOperateNote();
        operateComment =OperateComment.getCommentDetails();
        operateSocial=OperateSocial.getOperateSocial();
        info=LocalInformation.getLocalInformation().query();
    }
}

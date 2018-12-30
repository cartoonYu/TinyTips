package com.cartoon.tinytips.Personal.Collect;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.bean.table.Local.LocalInformation;
import com.cartoon.tinytips.bean.table.Operate.OperateComment;
import com.cartoon.tinytips.bean.table.Operate.OperateInformation;
import com.cartoon.tinytips.bean.table.Operate.OperateNote;
import com.cartoon.tinytips.bean.table.Operate.OperateSocial;
import com.cartoon.tinytips.util.Adapters.Personal.Collect.Collect;

import java.util.List;

public class CollectModel implements ICollect.Model {

    private OperateSocial operateSocial;

    private OperateNote operateNote;

    private OperateInformation operateInformation;

    private OperateComment operateComment;

    private Information information;

    @Override
    public void initData(ValueCallBack<List<Collect>> callBack) {
        callBack.onFail("系统错误");
    }

    public CollectModel(){
        operateNote=OperateNote.getOperateNote();
        operateSocial=OperateSocial.getOperateSocial();
        operateInformation=OperateInformation.getOperateInformation();
        operateComment =OperateComment.getCommentDetails();
        information=LocalInformation.getLocalInformation().query();
    }
}

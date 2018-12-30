package com.cartoon.tinytips.HomePage.Hot;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Operate.OperateNote;

import java.util.List;

public class HotModel implements IHot.Model {

    private int flag;

    private OperateNote operateNote;

    @Override
    public void initData(ValueCallBack<List<HotItem>> callBack){
        callBack.onFail("系统错误");
    }

    public HotModel(){
        flag=0;
        operateNote =OperateNote.getOperateNote();
    }
}

package com.cartoon.tinytips.Main;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;

import java.util.List;

public class MainModel implements IMain.Model {
    private List<Information> list;

    private Information info;

    private Information queryCondition;

    @Override
    public void getPersonalInformation(ValueCallBack<Information> callBack) {

    }
}

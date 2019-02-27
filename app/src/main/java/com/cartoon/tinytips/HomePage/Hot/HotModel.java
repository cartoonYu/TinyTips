package com.cartoon.tinytips.HomePage.Hot;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.table.Operate.OperateNote;
import com.cartoon.tinytips.bean.view.StatSocial;
import com.cartoon.tinytips.bean.view.check.CheckStatSocial;
import com.cartoon.tinytips.bean.view.check.imp.ICheckStatSocial;

import java.util.List;

public class HotModel implements IHot.Model {

    private ICheckStatSocial checkStatSocial;

    @Override
    public void initData(final ValueCallBack<List<StatSocial>> callBack){
        /*StatSocial statSocial=new StatSocial();
        checkStatSocial.query(statSocial, new IOperateBean<List<StatSocial>>() {
            @Override
            public void onSuccess(List<StatSocial> list) {
                callBack.onSuccess(list);
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail(msg);
            }
        });*/
        callBack.onFail("初始化热门列表错误");
    }

    public HotModel(){
        checkStatSocial=CheckStatSocial.getCheckStatSocial();
    }
}
